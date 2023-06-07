package Vista;

import Controlador.Conexion;
import Modelo.Contacto;
import java.util.Scanner;
import Modelo.*;

// CASO PRÁCTICO "AGENDA DE CONTACTOS" HECHO CON PROGRAMACIÓN ESTRUCTURADA (UNIDAD 5)
public class Main {

    //Creamos una instancia del objeto main para no tner que poner los metodos en staticç
    private static final Main main = new Main();
    // Creamos una instancia del objeto Conexion
    public static final Conexion SQL = new Conexion();
    // Creamos una instancia del objeto Agenda
    public static final Agenda agenda = new Agenda();

    // Variables auxiliares
    int opcion;     // opcion del menú
    String n, c, t; // nombre, correo y teléfono
    String buscar;  // término a buscar
    int pos;        // posicion
    int[] vpos;     // vector de posiciones

    // MAIN PRINCIPAL
    public static void main(String[] args) {
        main.switchCaseMenu();
    }

    public void switchCaseMenu() {
        //Conectamos con la base de datos
        SQL.conectarMySQL();
        // Bucle principal
        do {
            opcion = menu();

            switch (opcion) {
                case 1: // Ver contactos
                    agenda.imprimirTodos();
                    break;

                case 2: // Añadir contacto
                    Contacto nuevoContacto = nuevoContacto();
                    agenda.agregarContacto(nuevoContacto);
                    break;

                case 3: // Eliminar contacto
                    agenda.imprimirTodos();
                    int id = pedirIdContacto();
                    agenda.eliminarContacto(id);
                    break;

                case 4: // Buscar por nombre
                    System.out.print("Introduce el nombre del contacto: ");
                    String nombre = pedirString();
                    agenda.buscarContactoNombre(nombre);
                    break;

                case 5: // Buscar por teléfono
                    System.out.print("Itroduce el número del teléfono: ");
                    String numero = pedirString();
                    agenda.buscarContactoTelefono(numero);
                    break;

                case 6: // Buscar por correo
                    System.out.print("Itroduce el email: ");
                    String email = pedirString();
                    agenda.buscarContactoEmail(email);
                    break;

                case 7:
                    // Búsqueda global
                    
                    break;
                case 8:
                    // Salir
                    System.out.println("¡Gracias! ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }

            System.out.println("");
        } while (opcion != 8);
        // Desconectamos de la BD
        SQL.desconectarMySQL();
    }

    /**
     * FUNCIONES DEL MENÚ Y PEDIR DATOS AL USUARIO
     *
     * @return
     */
// Muestra el menú y devuelve la opción elegida por el usuario
    public static int menu() {

        System.out.println("1. Ver contactos");
        System.out.println("2. Agregar contacto.");
        System.out.println("3. Eliminar contacto.");
        System.out.println("4. Buscar por nombre.");
        System.out.println("5. Buscar por teléfono");
        System.out.println("6. Buscar por correo.");
        System.out.println("7. Búsqueda global.");
        System.out.println("8. Salir.");
        System.out.print("¿Opción? ");

        int opcion = pedirIntEnRango(1, 8);

        return opcion;
    }

    public static Contacto nuevoContacto() {
        System.out.print("Introduce nombre y apellidos: ");
        String fullname = pedirString();
        System.out.print("Introduce el número de telefono: ");
        String telephone = pedirString();
        System.out.print("Introduce el email: ");
        String email = pedirString();

        Contacto nuevoContacto = new Contacto(fullname, telephone, email);
        return nuevoContacto;
    }

    // Pide al usuario un valor int, una y otra vez hasta que responde con valor en rango
    public static int pedirIntEnRango(int min, int max) {

        Scanner in = new Scanner(System.in);
        int valor;

        do {
            valor = in.nextInt();
            if (valor < min || valor > max) {
                System.out.println("AVISO: No válido. Debe ser entre " + min + " y " + max);
                System.out.print("Vuelve a intentarlo: ");
            }
        } while (valor < min || valor > max);

        return valor;
    }

    // Pide al usuario un texto y lo devuelve
    public static String pedirString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    
    public static int pedirIdContacto() {
        Scanner in = new Scanner(System.in);
        System.out.print("Introduce el ID de contacto: ");
        int id = in.nextInt();
        return id;
    }
}
