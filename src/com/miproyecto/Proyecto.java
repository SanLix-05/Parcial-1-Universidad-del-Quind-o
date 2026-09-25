package devplus;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class Proyecto {
    private final int COSTOPORPROYECTO = 20000;
    private HashMap<Integer, List<Proyecto>> proyectosClientes = new HashMap<>();
    private String name;
    private String activadad;

    public Proyecto (String name, String actividad, HashMap<Integer, List<Proyecto>> proyectosClientes){
        this.name = name;
        this.activadad = actividad;
        this.proyectosClientes = proyectosClientes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivadad() {
        return activadad;
    }

    public void setActivadad(String activadad) {
        this.activadad = activadad;
    }

    //metodos

    public HashMap<Integer, List<Proyecto>> getProyectosClientes() {

        return proyectosClientes;
    }
}
