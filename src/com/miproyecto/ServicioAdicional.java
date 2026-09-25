package devplus;
import java.util.ArrayList;
import java.util.List;
public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private String disponibilidad;
    private static List<ServicioAdicional> serviciosRegistrados = new ArrayList<>();


    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, String disponibilidad) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public static List<ServicioAdicional> getServiciosRegistrados() {
        return serviciosRegistrados;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public boolean estaDisponible() {
        return disponibilidad.equalsIgnoreCase("Disponible");
    }

    public static void setServiciosRegistrados(List<ServicioAdicional> serviciosRegistrados) {
        ServicioAdicional.serviciosRegistrados = serviciosRegistrados;
    }
    public static void agregarServicio(ServicioAdicional servicio) {
        serviciosRegistrados.add(servicio);

}   public static ServicioAdicional buscarPorCodigo(String codigo) {
        for (ServicioAdicional s : serviciosRegistrados) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }


    return null; }}
