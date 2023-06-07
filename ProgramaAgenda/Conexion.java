package Controlador;

import Modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class Conexion {

    public String driver = "org.mariadb.jdbc.Driver";
    public String database = "AGENDA";
    public String hostname = "localhost";
    public String port = "3306";
    public String url = "jdbc:mariadb://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    public String username = "root";
    public String password = "123456789";
    public static Connection conn = null;
    public static ResultSet res = null;
    public static Statement stmt = null;
    public static Agenda agenda;

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

    public static boolean comprobarSiExisteEnBD(Contacto c) {
        // busca si existe el contacto (si tiene el mismo nombre y el mismo email, por ejemplo)
        // si existe devuelve true
        // si no existe devuelve false
        c = new Contacto(c.getNombre(), c.getTelefono(), c.getCorreo());
        try {
            String query = "SELECT * FROM contacts WHERE fullname = ? AND email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, c.getNombre());
            preparedStmt.setString(2, c.getCorreo());
            
            res = preparedStmt.executeQuery();
            
            return res.next();
        } catch (SQLException e) {
            System.out.println("[!] Este contacto ya existe en la base de datos. Detalles: ");
            e.printStackTrace();
            return false;
        }
    }

    public static void insertarContactoEnBD(Contacto c) {
        // insertar en BD
        try {
            if (comprobarSiExisteEnBD(c)) {
                System.out.print("No se ha podido crear el contacto.");
            } else {
                String query = " INSERT INTO contacts (fullname, telephone, email)" + " values (?, ?, ?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, c.getNombre());
                preparedStmt.setString(2, c.getTelefono());
                preparedStmt.setString(3, c.getCorreo());
                preparedStmt.execute();
            }
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido introducir el contacto. Detalles: ");
            e.printStackTrace();
        }
    }

    public static void eliminarContactoPorIDEnBD(int id) {
        try {
            String query = "DELETE FROM contacts WHERE contactid = ?";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido eliminar el contacto. Detalles: ");
            e.printStackTrace();
        }
    }

    public static ArrayList<Contacto> buscarPorNombreEnBD(String cadena) {
        // busca los contactos que tienen ese cadena o parte de ese cadena como nombre (nombre LIKE '%' + cadena + '%')
        ArrayList<Contacto> contactos = new ArrayList<>();
        Contacto contactoActual = null;
        try {
            String query = "SELECT * FROM contacts WHERE fullname LIKE ?";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "%" + cadena + "%");
            
            res = preparedStmt.executeQuery();
            
            while (res.next()) {
                int id = res.getInt("contactid");
                String name = res.getString("fullname");
                String telephone = res.getString("telephone");
                String email = res.getString("email");
                
                System.out.println("ID de contacto: " + id + ". " + "Nombre: " + name + ". " + "Teléfono: " + telephone + ". " + "Email: " + email);
            }
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido encontrar el/los contacto/s. Detalles: ");
            e.printStackTrace();
        }
        // devuelve en un arraylist las coincidencias
        return contactos;
    }

    public static ArrayList<Contacto> buscarPorEmailEnBD(String cadena) {
        // busca los contactos que tienen esa cadena en su email (email LIKE '%'+cadena+'%')
        // devuelve en un arraylist las coincidencias
        ArrayList<Contacto> contactos = new ArrayList<>();
        Contacto contactoActual;
        try {
            String query = "SELECT * FROM contacts WHERE email LIKE ?";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "%" + cadena + "%");
            
            res = preparedStmt.executeQuery();
            
            while (res.next()) {
                int id = res.getInt("contactid");
                String name = res.getString("fullname");
                String telephone = res.getString("telephone");
                String email = res.getString("email");
                
                System.out.println("ID de contacto: " + id + ". " + "Nombre: " + name + ". " + "Teléfono: " + telephone + ". " + "Email: " + email);
            }
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido encontrar el/los contacto/s. Detalles: ");
            e.printStackTrace();
        }
        // devuelve en un arraylist las coincidencias
        return contactos;
    }

    public static ArrayList<Contacto> buscarPorTelefonoEnBD(String cadena) {
        // busca los contactos que tienen esa cadena en su telefono (telefono LIKE '%'+cadena+'%')
        // devuelve en un arraylist las coincidencias
        ArrayList<Contacto> contactos = new ArrayList<>();
        Contacto contactoActual;

        try {
            String query = "SELECT * FROM contacts WHERE telephone LIKE ?";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "%" + cadena + "%");
            
            res = preparedStmt.executeQuery();
            
            while (res.next()) {
                int id = res.getInt("contactid");
                String name = res.getString("fullname");
                String telephone = res.getString("telephone");
                String email = res.getString("email");
                
                System.out.println("ID de contacto: " + id + ". " + "Nombre: " + name + ". " + "Teléfono: " + telephone + ". " + "Email: " + email);
            }
            
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido encontrar el/los contacto/s. Detalles: ");
            e.printStackTrace();
        }
        // devuelve en un arraylist las coincidencias
        return contactos;
    }

    public static boolean buscarGlobalEnBD(String cadenadetexto) {
        // busca los contactos que tienen esa cadena en alguno de sus campos
        // Para ello, llama a los método anteriores y concatena los resultados distintos
        // devuelve un único arraylist con todas las coincidencias (de contactos con diferente id)
        
        return false;
    }

    public static ArrayList<Contacto> leerContactosDesdeBD() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        Contacto contactoActual;
        try {
            stmt = conn.createStatement();
            String consulta = "SELECT contactid, fullname, telephone, email FROM CONTACTS";
            
            res = stmt.executeQuery(consulta);
            
            while (res.next()) {
                contactoActual = new Contacto(res.getString(1), res.getString(2), res.getString(3));
                contactos.add(contactoActual);
            }
        } catch (SQLException e) {
            System.out.println("[!] No se ha podido leer el contacto. Detalles: ");
            e.printStackTrace();
        }
        return contactos;
    }
}
