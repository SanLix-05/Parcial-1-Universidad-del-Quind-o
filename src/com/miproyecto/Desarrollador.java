package devplus;

import java.util.HashMap;

public class Desarrollador {

    private String codigo;
    private String equipoTrabajo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado;

    private HashMap<String, String> proyectosAsignados;

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

    public String getCodigo() {
        return codigo;
    }

    public String getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public String getNivel() {
        return nivel;
    }

    public int getMaxProyectosSimultaneos() {
        return maxProyectosSimultaneos;
    }

    public double getTarifaPorDia() {
        return tarifaPorDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEquipoTrabajo(String equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) {
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
    }

    public void setTarifaPorDia(double tarifaPorDia) {
        this.tarifaPorDia = tarifaPorDia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean puedeAsignarse() {
        return proyectosAsignados.size() < maxProyectosSimultaneos;
    }

    public boolean asignarProyecto(String codigoProyecto) {

        if (puedeAsignarse()) {

            proyectosAsignados.put(codigoProyecto, "Asignado");

            if (proyectosAsignados.size() == maxProyectosSimultaneos) {
                estado = "Ocupado";
            } else {
                estado = "Asignado";
            }

            return true;
        }

        return false;
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
        return estado.equalsIgnoreCase("Disponible");
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
    }

    public double calcularCosto(int dias) {
        return tarifaPorDia * dias;
    }



}

