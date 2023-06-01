package Controlador;

public class Contacto {

    // Atributos
    String nombre;
    String telefono;
    String correo;

    // Constructor
    public Contacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Devuelve un String con la información del contacto
    public String getAll() {
        return (nombre + " " + telefono + " " + correo);
    }
    
    // Imprime la información del contacto
    public void imprimir() {
        System.out.println(getAll());
    }

}
