package devplus;
import javax.swing.*;


public class Cliente{
    private String name;
    private String id;
    private String phone;
    private String country;
    private String representanteLegal;
    private String [] numeroProyecto;

    //Instancia del metodo constructor
    public Cliente(String name, String id, String phone, String country, String representanteLegal){
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.country = country;
        this.representanteLegal = representanteLegal;
    }

    //instancia de get y setters :]
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRepresentanteLegal() {
        return representanteLegal;
    }
    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    //Creacion de metodos
    public static Cliente registroCliente(){
        int respuesta = 0;
        String name = "";
        String id = "";
        String representanteLegal = "";
        String phone = "";
        String country = "";
        do{
            JOptionPane.showMessageDialog(null,"Hola querido cliente, agradecemos que haya comprado\n su proyecto con nosotros a continuacion vamos a hacer su registro");
            name = JOptionPane.showInputDialog("Ingrese su razon social/nombre");
            id = JOptionPane.showInputDialog("Ingrese su nit");
            representanteLegal = JOptionPane.showInputDialog("Ingrese el nombre del representante legal");
            phone = JOptionPane.showInputDialog("Ingrese el numero de conctacto de la empresa");
            country = JOptionPane.showInputDialog("Ingrese su pais");
            respuesta = JOptionPane.showConfirmDialog(null,"Compruebe que sus datos son correctos:" +
                    "\nRazon social " + name + "\nNit " + id + "\nRepresentante legal " + representanteLegal +
                    "\n Numero de contacto: " + phone + "\n Pais: " + country, "Confirme", JOptionPane.YES_NO_OPTION);
        }while(respuesta == JOptionPane.NO_OPTION);
        return new Cliente(name, id, phone, country, representanteLegal);
    }
    public static String [] crearProyecto(String name, String id, String phone, String country){
        int respuesta = 0;
        String nameProyecto = "";
        String eventoProyecto = "";
        //Se crea la lista para anadir el nombre y de que va a hacer el proyecto
        String [] list = new String[2];
        do{
            nameProyecto = JOptionPane.showInputDialog("Ingrese el nombre del proyecto:");
            eventoProyecto = JOptionPane.showInputDialog("Ingrese la actividad a realizar");
            respuesta = JOptionPane.showConfirmDialog(null,"Compruebe que sus datos son correctos:" +
                    "\nRazon social " + name + "\nNit " + id + "\nNombre de proyecto" + nameProyecto +
                    "\nActividad a realizar: " + eventoProyecto + "\n Pais: " + country, "\nConfirme", JOptionPane.YES_NO_OPTION);

        }while(respuesta == JOptionPane.NO_OPTION);
        list[0] = nameProyecto;
        list[1] = eventoProyecto;
        return list;
    }
}

