package Model;

import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity
@Table(name="usuarios")
public class Cliente {
    @Id
    private int usuarioId;
    private String nombre;
    private int Edad;
    private String correo;
    private String password;
    private String rol;
}
