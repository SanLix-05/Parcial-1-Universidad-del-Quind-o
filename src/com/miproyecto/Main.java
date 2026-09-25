package devplus;
import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {

        int respuesta = 0;
        int opcion = 0;
        do {
            respuesta = Integer.parseInt(
                    JOptionPane.showInputDialog("DEVPLUS\n" + "Inicias sesion como:\n" + "1. Cliente\n" +
                            "2. Administrador\n" + "3. Salir\n\n" + "Ingrese una opción:")
            );
            opcion = respuesta;
            switch (opcion){
                case 1:
                    respuesta = Integer.parseInt(JOptionPane.showInputDialog("DEVPLUS\n" + "1. Registrarse\n" + "2.Crear proyecto \n"
                    + "3. Servicios adicionales\n" + " 4.salir"));
                    if (respuesta == 1){
                        Cliente.registroCliente();
                    } else if (respuesta == 2) {
                        
                    }

            }


        } while (opcion < 1 || opcion > 7);
    }}


