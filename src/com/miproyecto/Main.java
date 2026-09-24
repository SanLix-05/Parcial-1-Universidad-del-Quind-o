package devplus;
import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "DEVPLUS\n" +
                                    "1. Registrar cliente\n" +
                                    "2. Registrar desarrollador\n" +
                                    "3. Registrar servicio\n" +
                                    "4. Registrar proyecto\n" +
                                    "5. Consultar cliente\n" +
                                    "6. Calcular ingresos\n" +
                                    "7. Salir\n\n" +
                                    "Ingrese una opción:"
                    )
            );

            if (opcion < 1 || opcion > 7) {
                JOptionPane.showMessageDialog(null,
                        "Opción no válida. Ingrese un número del 1 al 7.");
            }

        } while (opcion < 1 || opcion > 7);
    }}


