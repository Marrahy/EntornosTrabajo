package Modelo;

import Controlador.Conexion;
import java.util.ArrayList;

public class Agenda {
    
    //Instanciamos el objeto Conexion
    Conexion conn = new Conexion();
    
    public static void imprimirTodos() {
        ArrayList<Contacto> listadoDeContactos;
        Contacto contactoActual;
        int totalContactos;
        // Leemos los contactos
        listadoDeContactos = Conexion.leerContactosDesdeBD();
        totalContactos = listadoDeContactos.size();
        if (totalContactos == 0) {
            System.out.println("No hay contactos");
        } else {
            System.out.println("Número de contactos: " + totalContactos);
            for (int i = 0; i < listadoDeContactos.size(); i++) {
                // Extraemos el contacto del ArrayList
                contactoActual = Conexion.leerContactosDesdeBD().get(i);
                // Imprimimos el contacto
                contactoActual.imprimir();
            }
        }
    }
    
    public void agregarContacto(Contacto c) {
        conn.insertarContactoEnBD(c);
    }
    
    public void eliminarContacto(int id) {
        conn.eliminarContactoPorIDEnBD(id);
    }
    
    public void comprobarExistencia(Contacto c) {
        conn.comprobarSiExisteEnBD(c);
    }
    
    public void buscarContactoNombre(String nombre) {
        conn.buscarPorNombreEnBD(nombre);
    }
    
    public void buscarContactoEmail(String email) {
        conn.buscarPorEmailEnBD(email);
    }
    
    public void buscarContactoTelefono(String telefono) {
        conn.buscarPorTelefonoEnBD(telefono);
    }
    
    public void busquedaGlobal(String cadena) {
        conn.buscarGlobalEnBD(cadena);
    }
}
