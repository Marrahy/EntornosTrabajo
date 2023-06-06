package Modelo;

import java.util.ArrayList;
import Controlador.Contacto;

public class Agenda {
    
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
}
