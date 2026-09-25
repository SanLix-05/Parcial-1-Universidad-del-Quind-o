package devplus;

import javax.swing.*;
import java.util.HashMap;

public class Cliente {
    private String name;
    private String id; // NIT
    private String phone;
    private String email;
    private String country;
    private String representanteLegal;

    // Colección estática de todos los clientes registrados (clave = NIT)
    private static HashMap<String, Cliente> clientesRegistrados = new HashMap<>();

    // Constructor (ahora con 6 parámetros, incluyendo email)
    public Cliente(String name, String id, String phone, String email, String country, String representanteLegal) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.representanteLegal = representanteLegal;
    }

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRepresentanteLegal() { return representanteLegal; }
    public void setRepresentanteLegal(String representanteLegal) { this.representanteLegal = representanteLegal; }

    // ----- Métodos -----

    public static Cliente registroCliente() {
        int respuesta;
        String name, id, representanteLegal, phone, email, country;

        do {
            JOptionPane.showMessageDialog(null,
                    "Hola querido cliente, agradecemos que haya comprado\n" +
                            "su proyecto con nosotros, a continuación vamos a hacer su registro");

            name = JOptionPane.showInputDialog("Ingrese su razón social/nombre");
            id = JOptionPane.showInputDialog("Ingrese su NIT");
            representanteLegal = JOptionPane.showInputDialog("Ingrese el nombre del representante legal");
            phone = JOptionPane.showInputDialog("Ingrese el número de contacto de la empresa");
            email = JOptionPane.showInputDialog("Ingrese el correo electrónico de contacto");
            country = JOptionPane.showInputDialog("Ingrese su país");

            respuesta = JOptionPane.showConfirmDialog(null,
                    "Compruebe que sus datos son correctos:" +
                            "\nRazón social: " + name +
                            "\nNIT: " + id +
                            "\nRepresentante legal: " + representanteLegal +
                            "\nNúmero de contacto: " + phone +
                            "\nCorreo: " + email +
                            "\nPaís: " + country,
                    "Confirme", JOptionPane.YES_NO_OPTION);

        } while (respuesta == JOptionPane.NO_OPTION);

        Cliente nuevoCliente = new Cliente(name, id, phone, email, country, representanteLegal);
        clientesRegistrados.put(id, nuevoCliente);

        return nuevoCliente;
    }

    // ----- Métodos estáticos de gestión de la colección -----

    public static HashMap<String, Cliente> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public static Cliente buscarPorNit(String nit) {
        return clientesRegistrados.get(nit);
    }

    public static Cliente buscarPorTelefono(String phone) {
        for (Cliente c : clientesRegistrados.values()) {
            if (c.getPhone() != null && c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }
}