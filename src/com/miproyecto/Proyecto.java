package devplus;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Proyecto {
    private final int COSTOPORPROYECTO = 20000;
    private static HashMap<String, List<Proyecto>> proyectosClientes = new HashMap<>();

    private String name;
    private String actividad;

    public Proyecto(String name, String actividad) {
        this.name = name;
        this.actividad = actividad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getCOSTOPORPROYECTO() {
        return COSTOPORPROYECTO;
    }

    // Métodos
    public static void agregarProyecto(String idCliente, String[] datosProyecto) {
        Proyecto nuevoProyecto = new Proyecto(datosProyecto[0], datosProyecto[1]);


        if (!proyectosClientes.containsKey(idCliente)) {
            proyectosClientes.put(idCliente, new ArrayList<>());
        }


        proyectosClientes.get(idCliente).add(nuevoProyecto);
    }

    public static HashMap<String, List<Proyecto>> getProyectosClientes() {
        return proyectosClientes;
    }
}