package Model;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    ///@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCliente;
    private String nombres;
    private String apellidos;
    private String Doctype;
    private int Edad;
    private String ciudad;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDoctype() {
        return Doctype;
    }

    public void setDoctype(String doctype) {
        Doctype = doctype;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
