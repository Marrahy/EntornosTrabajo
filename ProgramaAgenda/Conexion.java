



/*
Proyecto Agenda - Archivo Conexion.java Compañía DAM
Licencia Creative Commons BY-NC-SA 4.0
https://creativecommons.org/licenses/by-nc-sa/4.0/
 */
/**
 *
 * @author Sergi <sergi.marrahy at sergi.org>
 * @version 1.0
 * @date 24 may. 2023 10:19:56
 */
import Controlador.Contacto;
import java.sql.*;
import java.util.ArrayList;

public class Conexion { //Modelo

    public String driver = "org.mariadb.jdbc.Driver";
    public String database = "AGENDA";
    public String hostname = "localhost";
    public String port = "3306";

    public String url = "jdbc:mariadb://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    public String username = "usr_agenda";
    public String password = "pwd1234";
    public static Connection conn = null;

    public void conectarMySQL() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("[!] Estás conectado a la BD");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[!] No se ha podido conectar con la BD. Detalles: ");
            e.printStackTrace();
        }
    }

    public void desconectarMySQL() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("[!] Estás desconectado de la BD");
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido desconectar con la BD. Detalles: ");
            e.printStackTrace();
        }
    }

    public static ArrayList<Contacto> leerContactosDesdeBD() {
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return al_contactos;
    }

    public static ArrayList<Contacto> buscarPorNombreEnBD(String cadena) {
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS WHERE fullname LIKE '%" + cadena + "%'";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return al_contactos;
    }

    public static void insertarContactoEnBD(Contacto c) {
        // insertar en BD
        Contacto nuevoContacto = c;
        String nombre = nuevoContacto.getNombre();
        String telephone = nuevoContacto.getTelefono();
        String email = nuevoContacto.getCorreo();
        try {
            Statement stmt = conn.createStatement();
            String consulta = "INSERT INTO contacts(nombre, telephone, email)" + "values (?, ?, ?)";            
            PreparedStatement prepareStmt = conn.prepareStatement(consulta);
            prepareStmt.setString(1, nombre);
            prepareStmt.setString(2, telephone);
            prepareStmt.setString(3, email);
            
            prepareStmt.execute();
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido crear el contacto. Detalles: ");
            e.printStackTrace();
        }
    }

    public static boolean eliminarContactoPorIDEnBD(Contacto c) {
        // comprueba si existe ese id
        Contacto contactoParaEliminar = c;
        String nombre = contactoParaEliminar.getNombre();
        String telephone = contactoParaEliminar.getTelefono();
        String email = contactoParaEliminar.getCorreo();
        
        try {
            String consulta = "SELECT fullname, telephone, email FROM contacts WHERE fullname = ? AND telephone = ? AND email = ?";
            PreparedStatement prepareStmt = conn.prepareStatement(consulta);
            prepareStmt.setString(1, nombre);
            prepareStmt.setString(2, telephone);
            prepareStmt.setString(3, email);
            ResultSet res = prepareStmt.executeQuery(consulta);
            
            if (res.next()) {
                int contactID = res.getInt("contactid");
            }
            
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido crear el contacto. Detalles: ");
            e.printStackTrace();
        }
    }

    public static ArrayList<Contacto> buscarGlobal(String cadena) {
        
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS WHERE fullname LIKE '%" + cadena + "%' OR telephone LIKE '%" + cadena + "%' OR email LIKE '%" + cadena + "%'";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
            // devuelve en un arraylist las coincidencias
            return al_contactos;
    }
    
public static ArrayList<Contacto> buscarPorEmailEnBD(String cadena) {
        // busca los contactos que tienen esa cadena en su email (email LIKE '%'+cadena+'%')
        // devuelve en un arraylist las coincidencias
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS WHERE email LIKE '%" + cadena + "%'";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return al_contactos;
    }

    public static ArrayList<Contacto> buscarPorTelefonoEnBD(String cadena) {
        // busca los contactos que tienen esa cadena en su telefono (telefono LIKE '%'+cadena+'%')
        // devuelve en un arraylist las coincidencias
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS WHERE telephone LIKE '%" + cadena + "%'";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return al_contactos;
    }

    public static ArrayList<Contacto> buscarGlobalEnBD(String cadenadetexto) {
        // busca los contactos que tienen esa cadena en alguno de sus campos
        // Para ello, llama a los método anteriores y concatena los resultados distintos
        // devuelve un único arraylist con todas las coincidencias (de contactos con diferente id)
        ArrayList<Contacto> al_contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS WHERE fullname OR email OR telephone LIKE '%" + cadenadetexto + "%'";
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                al_contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return al_contactos;
    }
}
