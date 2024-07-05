package com.login.springlogin.DTO;

public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String nombre;

    public UserDTO() {
    }
    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public UserDTO(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    public UserDTO(int id, String email, String password, String nombre) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", password=" + password + ", nombre=" + nombre + "]";
    }
    
    
    
}
