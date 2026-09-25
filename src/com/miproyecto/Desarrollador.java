package devplus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Desarrollador {

    private String codigo;
    private String equipoTrabajo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado;

    // codigoProyecto -> [fechaInicio, fechaEntrega]
    private HashMap<String, LocalDate[]> proyectosAsignados;

    private static List<Desarrollador> desarrolladoresRegistrados = new ArrayList<>();

    public Desarrollador(String codigo,
                         String equipoTrabajo,
                         String nivel,
                         int maxProyectosSimultaneos,
                         double tarifaPorDia,
                         String estado) {

        this.codigo = codigo;
        this.equipoTrabajo = equipoTrabajo;
        this.nivel = nivel;
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
        this.tarifaPorDia = tarifaPorDia;
        this.estado = estado;

        proyectosAsignados = new HashMap<>();
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getEquipoTrabajo() { return equipoTrabajo; }
    public void setEquipoTrabajo(String equipoTrabajo) { this.equipoTrabajo = equipoTrabajo; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public int getMaxProyectosSimultaneos() { return maxProyectosSimultaneos; }
    public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) { this.maxProyectosSimultaneos = maxProyectosSimultaneos; }

    public double getTarifaPorDia() { return tarifaPorDia; }
    public void setTarifaPorDia(double tarifaPorDia) { this.tarifaPorDia = tarifaPorDia; }

    public String getEstado() { return estado; }

    // ----- Lógica de asignación -----

    public boolean puedeAsignarse() {
        return proyectosAsignados.size() < maxProyectosSimultaneos;
    }

    public boolean estaDisponibleEnFechas(LocalDate inicio, LocalDate entrega) {
        for (LocalDate[] rango : proyectosAsignados.values()) {
            LocalDate inicioExistente = rango[0];
            LocalDate entregaExistente = rango[1];

            boolean seSolapan = !(entrega.isBefore(inicioExistente) || inicio.isAfter(entregaExistente));
            if (seSolapan) {
                return false;
            }
        }
        return true;
    }

    public boolean asignarProyecto(String codigoProyecto, LocalDate inicio, LocalDate entrega) {

        if (!estaDisponible()) {
            return false;
        }
        if (!puedeAsignarse()) {
            return false;
        }
        if (!estaDisponibleEnFechas(inicio, entrega)) {
            return false;
        }

        proyectosAsignados.put(codigoProyecto, new LocalDate[]{inicio, entrega});

        if (proyectosAsignados.size() >= maxProyectosSimultaneos) {
            estado = "Ocupado";
        } else {
            estado = "Asignado";
        }

        return true;
    }

    public void finalizarProyecto(String codigoProyecto) {

        proyectosAsignados.remove(codigoProyecto);

        if (proyectosAsignados.isEmpty()) {
            estado = "Disponible";
        } else {
            estado = "Asignado";
        }
    }

    public boolean estaDisponible() {
        return estado.equalsIgnoreCase("Disponible") || estado.equalsIgnoreCase("Asignado");
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
    }

    public void enviarACapacitacion() {
        estado = "En capacitación";
    }

    public double calcularCosto(int dias) {
        return tarifaPorDia * dias;
    }

    // ----- Métodos estáticos de gestión de la colección -----

    public static void agregarDesarrollador(Desarrollador dev) {
        desarrolladoresRegistrados.add(dev);
    }

    public static List<Desarrollador> getDesarrolladoresRegistrados() {
        return desarrolladoresRegistrados;
    }

    public static Desarrollador buscarPorCodigo(String codigo) {
        for (Desarrollador dev : desarrolladoresRegistrados) {
            if (dev.getCodigo().equals(codigo)) {
                return dev;
            }
        }
        return null;
    }
}