package Modelo;

import Controlador.Contacto;
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
        return false;
    }

    public static void insertarContactoEnBD(Contacto c) {
        // insertar en BD
        try {
            String query = " INSERT INTO contacts (fullname, telephone, email)" + " values (?, ?, ?)";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, c.getNombre());
            preparedStmt.setString(2, c.getTelefono());     //ABRE LOS PDFS DE ENTORNOS EN AULES Y MIRA LOS LINKS DE INSERTAR - ELIMINAR - UPDATEAR Y HAZ LOS METODOS RESTANTES
            preparedStmt.setString(3, c.getCorreo());
            preparedStmt.execute();
            
        } catch (SQLException e){
            System.out.println("[!] No se ha podido introducir el contacto. Detalles: ");
            e.printStackTrace();
        }
    }

    public static boolean eliminarContactoPorIDEnBD(Contacto c) {
// comprueba si existe ese id
// si existe, lo borra y devuelve true
// si no existe, devuelve false
        return false;
    }

    public static ArrayList<Contacto> buscarPorNombreEnBD(String cadena) {
        // busca los contactos que tienen ese cadena o parte de ese cadena como nombre (nombre LIKE '%' + cadena + '%')

        ArrayList<Contacto> contacto = new ArrayList<>();
        return contacto;
        // devuelve en un arraylist las coincidencias
    }

    public static ArrayList<Contacto> buscarPorEmailEnBD(String cadena) {
// busca los contactos que tienen esa cadena en su email (email LIKE '%'+cadena+'%')
// devuelve en un arraylist las coincidencias
        ArrayList<Contacto> contacto = new ArrayList<>();
        return contacto;
    }

    public static boolean buscarPorTelefonoEnBD(String cadena) {
// busca los contactos que tienen esa cadena en su telefono (telefono LIKE '%'+cadena+'%')
// devuelve en un arraylist las coincidencias
        return false;
    }

    public static boolean buscarGlobalEnBD(String cadenadetexto) {
// busca los contactos que tienen esa cadena en alguno de sus campos
// Para ello, llama a los método anteriores y concatena los resultados distintos
// devuelve un único arraylist con todas las coincidencias (de contactos con diferente id)
        return false;
    }

    public static ArrayList<Contacto> leerContactosDesdeBD() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        Contacto contactoActual;
        try {
            Statement stmt = conn.createStatement();
            String consulta = "SELECT fullname, telephone, email FROM CONTACTS";
            ResultSet res = stmt.executeQuery(consulta);
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
