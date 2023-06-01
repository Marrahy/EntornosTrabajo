package Controlador;

import Modelo.Conexion;
import java.util.ArrayList;

public class Agenda {

    public Agenda() {
        
    }
    // Imprime la información de todos los contactos
    public static void imprimirTodos() {
        ArrayList<Contacto> listadoDeContactos;
        Contacto contactoActual;
        int totalContactos;
        // Leemos los contactos
        listadoDeContactos = Conexion.leerContactosDesdeBD();
        totalContactos = listadoDeContactos.size();
        
        if (totalContactos == 0) {
            System.out.println("[!] Listamos todos los contactos (no encontramos ninguno)");
        } else {
            System.out.println("[*] Listamos todos los contactos (encontramos " + totalContactos + "):");
            for (int i = 0; i < listadoDeContactos.size(); i++) {
                // Extraemos el contacto del ArrayList
                contactoActual = Conexion.leerContactosDesdeBD().get(i);
                // Imprimimos el contacto
                contactoActual.imprimir();
            }
        }
    }
    
    public static boolean comprobarNombre(String nombre) {
        if (Conexion.comprobarNombre(nombre)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean 
}
