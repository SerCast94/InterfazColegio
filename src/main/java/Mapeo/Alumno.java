package Mapeo;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String Apellido;

    @Column(name = "telefono", length = 20)
    private String Telefono;

    @Column(name = "email", unique = true, length = 150)
    private String Email;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    private enum Estado{
        activo, inactivo
    };

    public Alumno() {}

    public Alumno(int ID, String nombre, String apellido, String telefono, String email) {
        this.ID = ID;
        Nombre = nombre;
        Apellido = apellido;
        Telefono = telefono;
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
