package devplus;

public class Desarrollador {
    private String codigo;
    private String equipoTrabajo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado;

    public Desarrollador(String codigo, String equipoTrabajo,
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
        this.estado = estado;}
}