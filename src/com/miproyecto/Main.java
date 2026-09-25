package devplus;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static Empresa empresa = new Empresa(
            "DevPlus",
            "900123456-7",
            "Armenia, Barrio 7 de Agosto, Mz 25 # 67",
            "601 555 0000",
            "www.devplus.com"
    );


    public static void main(String[] args) {

        int opcion;
        do {
            opcion = leerEntero("DEVPLUS\n" +
                    "Inicia sesión como:\n" +
                    "1. Cliente\n" +
                    "2. Administrador\n" +
                    "3. Salir\n\n" +
                    "Ingrese una opción:");

            switch (opcion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Gracias por usar DEVPLUS. ¡Hasta pronto!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (opcion != 3);
    }

    // ---------------- MENÚ CLIENTE ----------------
    private static void menuCliente() {
        int opcion;
        do {
            opcion = leerEntero("DEVPLUS - CLIENTE\n" +
                    "1. Registrarse\n" +
                    "2. Crear proyecto\n" +
                    "3. Ver servicios adicionales\n" +
                    "4. Volver");

            switch (opcion) {
                case 1:
                    Cliente.registroCliente();
                    break;
                case 2:
                    crearProyectoFlujo();
                    break;
                case 3:
                    verServiciosDisponibles();
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void crearProyectoFlujo() {
        String nit = JOptionPane.showInputDialog("Ingrese el NIT de la empresa (sin puntos ni comas):");

        Cliente cliente = Cliente.buscarPorNit(nit);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Este cliente no está registrado. Regístrese primero.");
            return;
        }

        String codigo = JOptionPane.showInputDialog("Ingrese el código del proyecto:");
        String nombreProyecto = JOptionPane.showInputDialog("Ingrese el nombre del proyecto:");
        String actividad = JOptionPane.showInputDialog("Ingrese la actividad a realizar:");

        LocalDate fechaSolicitud = LocalDate.now();
        LocalDate fechaInicio;
        LocalDate fechaEntrega;

        while (true) {
            fechaInicio = leerFecha("Ingrese la fecha de inicio (AAAA-MM-DD):");
            fechaEntrega = leerFecha("Ingrese la fecha de entrega (AAAA-MM-DD):");

            if (Proyecto.fechasValidas(fechaSolicitud, fechaInicio, fechaEntrega)) {
                break;
            }
            JOptionPane.showMessageDialog(null,
                    "Fechas inválidas: la entrega debe ser posterior al inicio,\n" +
                            "y el inicio no puede ser anterior a hoy. Intente de nuevo.");
        }

        String metodoPago = JOptionPane.showInputDialog(
                "Ingrese el método de pago:\n(Tarjeta de crédito / Transferencia bancaria / Efectivo)");

        Proyecto proyecto = new Proyecto(codigo, nombreProyecto, actividad, nit,
                fechaSolicitud, fechaInicio, fechaEntrega, metodoPago);

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea asignar desarrolladores al proyecto?",
                "Confirme", JOptionPane.YES_NO_OPTION);
        while (respuesta == JOptionPane.YES_OPTION) {
            String codDev = JOptionPane.showInputDialog("Ingrese el código del desarrollador:");
            Desarrollador dev = Desarrollador.buscarPorCodigo(codDev);

            if (dev == null) {
                JOptionPane.showMessageDialog(null, "Desarrollador no encontrado.");
            } else {
                boolean asignado = proyecto.asignarDesarrollador(dev);
                JOptionPane.showMessageDialog(null, asignado
                        ? "Desarrollador asignado correctamente."
                        : "No se pudo asignar (no disponible, sin cupo, o fechas cruzadas).");
            }

            respuesta = JOptionPane.showConfirmDialog(null, "¿Desea asignar otro desarrollador?",
                    "Confirme", JOptionPane.YES_NO_OPTION);
        }

        respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar servicios adicionales?",
                "Confirme", JOptionPane.YES_NO_OPTION);
        while (respuesta == JOptionPane.YES_OPTION) {
            String codServicio = JOptionPane.showInputDialog("Ingrese el código del servicio:");
            ServicioAdicional servicio = ServicioAdicional.buscarPorCodigo(codServicio);

            if (servicio == null) {
                JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
            } else if (!servicio.estaDisponible()) {
                JOptionPane.showMessageDialog(null, "El servicio no está disponible.");
            } else {
                proyecto.agregarServicio(servicio);
                JOptionPane.showMessageDialog(null, "Servicio agregado correctamente.");
            }

            respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro servicio?",
                    "Confirme", JOptionPane.YES_NO_OPTION);
        }

        Proyecto.agregarProyecto(nit, proyecto);

        JOptionPane.showMessageDialog(null,
                "Proyecto creado exitosamente.\nValor total: $" + proyecto.getValorTotal());
    }

    private static void verServiciosDisponibles() {
        List<ServicioAdicional> servicios = ServicioAdicional.getServiciosRegistrados();
        if (servicios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay servicios registrados todavía.");
            return;
        }
        StringBuilder sb = new StringBuilder("Servicios disponibles:\n");
        for (ServicioAdicional s : servicios) {
            sb.append(s.getCodigo()).append(" - ").append(s.getNombre())
                    .append(" ($").append(s.getPrecio()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // ---------------- MENÚ ADMINISTRADOR ----------------
    private static void menuAdministrador() {
        int opcion;
        do {
            opcion = leerEntero("DEVPLUS - ADMINISTRADOR\n" +
                    "1. Registrar desarrollador\n" +
                    "2. Registrar servicio adicional\n" +
                    "3. Consultar ingresos por fecha\n" +
                    "4. Consultar si un teléfono es número perfecto\n" +
                    "5. Cambiar estado de un proyecto\n" +
                    "6. Ver datos de la empresa\n" +
                    "7. Volver");

            switch (opcion) {
                case 1:
                    registrarDesarrollador();
                    break;
                case 2:
                    registrarServicio();
                    break;
                case 3:
                    consultarIngresosPorFecha();
                    break;
                case 4:
                    consultarNumeroPerfecto();
                    break;
                case 5:
                    cambiarEstadoProyecto();
                    break;
                case 6:
                    verDatosEmpresa();
                    break;
                case 7:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 7);
    }

    private static void registrarDesarrollador() {
        String codigo = JOptionPane.showInputDialog("Código del desarrollador:");
        String equipo = JOptionPane.showInputDialog("Equipo de trabajo:");
        String nivel = JOptionPane.showInputDialog("Nivel (Junior / Semisenior / Senior):");
        int maxProyectos = leerEntero("Cantidad máxima de proyectos simultáneos:");
        double tarifa = leerDouble("Tarifa por día:");

        Desarrollador dev = new Desarrollador(codigo, equipo, nivel, maxProyectos, tarifa, "Disponible");
        Desarrollador.agregarDesarrollador(dev);

        JOptionPane.showMessageDialog(null, "Desarrollador registrado correctamente.");
    }

    private static void registrarServicio() {
        String codigo = JOptionPane.showInputDialog("Código del servicio:");
        String nombre = JOptionPane.showInputDialog("Nombre del servicio:");
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        double precio = leerDouble("Precio:");

        ServicioAdicional servicio = new ServicioAdicional(codigo, nombre, descripcion, precio, "Disponible");
        ServicioAdicional.agregarServicio(servicio);

        JOptionPane.showMessageDialog(null, "Servicio registrado correctamente.");
    }

    private static void consultarIngresosPorFecha() {
        LocalDate fecha = leerFecha("Ingrese la fecha a consultar (AAAA-MM-DD):");
        double total = Proyecto.ingresosPorFecha(fecha);
        JOptionPane.showMessageDialog(null, "Ingresos del " + fecha + ": $" + total);
    }

    private static void consultarNumeroPerfecto() {
        String phone = JOptionPane.showInputDialog("Ingrese el número de teléfono a consultar:");
        Cliente cliente = Cliente.buscarPorTelefono(phone);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con ese teléfono.");
            return;
        }

        boolean esPerfecto = esNumeroPerfecto(phone);
        JOptionPane.showMessageDialog(null,
                "Cliente: " + cliente.getName() +
                        "\n¿Es número perfecto? " + (esPerfecto ? "Sí" : "No"));
    }

    private static void cambiarEstadoProyecto() {
        String nit = JOptionPane.showInputDialog("NIT del cliente:");
        String codigoProyecto = JOptionPane.showInputDialog("Código del proyecto:");

        List<Proyecto> proyectos = Proyecto.getProyectosClientes().get(nit);
        if (proyectos == null) {
            JOptionPane.showMessageDialog(null, "El cliente no tiene proyectos registrados.");
            return;
        }

        for (Proyecto p : proyectos) {
            if (p.getCodigo().equals(codigoProyecto)) {
                String nuevoEstado = JOptionPane.showInputDialog(
                        "Nuevo estado (Pendiente / Confirmado / En curso / Finalizado / Cancelado):");
                p.cambiarEstado(nuevoEstado);
                JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
    }

    private static void verDatosEmpresa() {
        JOptionPane.showMessageDialog(null,
                "Nombre comercial: " + empresa.getNombreComercial() +
                        "\nNIT: " + empresa.getNit() +
                        "\nDirección: " + empresa.getDireccion() +
                        "\nTeléfono: " + empresa.getTelefono() +
                        "\nPágina web: " + empresa.getPagina());
    }

    // ---------------- UTILIDADES ----------------

    private static boolean esNumeroPerfecto(String numeroStr) {
        long numero;
        try {
            numero = Long.parseLong(numeroStr.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return false;
        }
        if (numero <= 1) return false;

        long suma = 0;
        for (long i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(JOptionPane.showInputDialog(mensaje));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(JOptionPane.showInputDialog(mensaje));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato inválido. Use AAAA-MM-DD.");
            }
        }
    }
}