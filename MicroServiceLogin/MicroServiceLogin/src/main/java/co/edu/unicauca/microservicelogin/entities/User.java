package co.edu.unicauca.microservicelogin.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 15, nullable = false)
    private long id;
    @Column(length = 20, nullable = false, unique = true)
    private String username;
    @Column(length = 15, nullable = false)
    private String contrasenia;
    @Column(length = 20, nullable = false)
    private String rol;
    @Column(length = 40, nullable = false)
    private String email;
    public User() {

    }

    public User(String password, long id, String rol, String username) {
        this.contrasenia = password;
        this.id = id;
        this.rol = rol;
        this.username = username;
    }
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
