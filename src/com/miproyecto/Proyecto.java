package devplus;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Proyecto {

    private static HashMap<String, List<Proyecto>> proyectosClientes = new HashMap<>();

    private String codigo;
    private String name;
    private String actividad;
    private String clienteNit;
    private LocalDate fechaSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;
    private String estado;
    private String metodoPago;
    private double valorTotal;

    private List<Desarrollador> desarrolladoresAsignados = new ArrayList<>();
    private List<ServicioAdicional> serviciosUtilizados = new ArrayList<>();

    public Proyecto(String codigo, String name, String actividad, String clienteNit,
                    LocalDate fechaSolicitud, LocalDate fechaInicio, LocalDate fechaEntrega,
                    String metodoPago) {
        this.codigo = codigo;
        this.name = name;
        this.actividad = actividad;
        this.clienteNit = clienteNit;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.metodoPago = metodoPago;
        this.estado = "Pendiente";
        this.valorTotal = 0.0;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getActividad() { return actividad; }
    public void setActividad(String actividad) { this.actividad = actividad; }

    public String getClienteNit() { return clienteNit; }

    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDate fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getEstado() { return estado; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public double getValorTotal() { return valorTotal; }

    public List<Desarrollador> getDesarrolladoresAsignados() { return desarrolladoresAsignados; }
    public List<ServicioAdicional> getServiciosUtilizados() { return serviciosUtilizados; }

    public long getDiasDesarrollo() {
        if (fechaInicio == null || fechaEntrega == null) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaEntrega);
    }

    public static boolean fechasValidas(LocalDate solicitud, LocalDate inicio, LocalDate entrega) {
        if (inicio.isBefore(solicitud)) return false;
        if (entrega.isBefore(inicio)) return false;
        return true;
    }

    public boolean asignarDesarrollador(Desarrollador dev) {
        boolean ok = dev.asignarProyecto(this.codigo, this.fechaInicio, this.fechaEntrega);
        if (ok) {
            desarrolladoresAsignados.add(dev);
            calcularValorTotal();
        }
        return ok;
    }

    public void agregarServicio(ServicioAdicional servicio) {
        if (servicio.estaDisponible()) {
            serviciosUtilizados.add(servicio);
            calcularValorTotal();
        }
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        if (nuevoEstado.equalsIgnoreCase("Confirmado")) {
            for (Desarrollador dev : desarrolladoresAsignados) {
                if (!dev.puedeAsignarse()) {
                    dev.cambiarEstado("Ocupado");
                } else {
                    dev.cambiarEstado("Asignado");
                }
            }
        } else if (nuevoEstado.equalsIgnoreCase("Finalizado") || nuevoEstado.equalsIgnoreCase("Cancelado")) {
            for (Desarrollador dev : desarrolladoresAsignados) {
                dev.finalizarProyecto(this.codigo);
            }
        }
    }

    public double calcularValorTotal() {
        double costoDesarrolladores = 0;
        long dias = getDiasDesarrollo();
        for (Desarrollador dev : desarrolladoresAsignados) {
            costoDesarrolladores += dev.calcularCosto((int) dias);
        }

        double costoServicios = 0;
        for (ServicioAdicional servicio : serviciosUtilizados) {
            costoServicios += servicio.getPrecio();
        }

        double subtotal = costoDesarrolladores + costoServicios;
        double descuento = calcularDescuentoClienteFrecuente(subtotal);

        this.valorTotal = subtotal - descuento;
        return this.valorTotal;
    }

    private double calcularDescuentoClienteFrecuente(double subtotal) {
        if (clienteNit == null) return 0;

        List<Proyecto> proyectosPrevios = proyectosClientes.getOrDefault(clienteNit, new ArrayList<>());
        int cantidad = proyectosPrevios.size();

        double porcentaje;
        if (cantidad >= 5) {
            porcentaje = 0.15;
        } else if (cantidad >= 3) {
            porcentaje = 0.10;
        } else if (cantidad >= 1) {
            porcentaje = 0.05;
        } else {
            porcentaje = 0.0;
        }

        return subtotal * porcentaje;
    }

    public static void agregarProyecto(String idCliente, Proyecto proyecto) {
        if (!proyectosClientes.containsKey(idCliente)) {
            proyectosClientes.put(idCliente, new ArrayList<>());
        }
        proyectosClientes.get(idCliente).add(proyecto);
    }

    public static HashMap<String, List<Proyecto>> getProyectosClientes() {
        return proyectosClientes;
    }

    public static double ingresosPorFecha(LocalDate fecha) {
        double total = 0;
        for (List<Proyecto> proyectos : proyectosClientes.values()) {
            for (Proyecto p : proyectos) {
                if (p.getFechaSolicitud() != null && p.getFechaSolicitud().equals(fecha)) {
                    total += p.getValorTotal();
                }
            }
        }
        return total;
    }
}