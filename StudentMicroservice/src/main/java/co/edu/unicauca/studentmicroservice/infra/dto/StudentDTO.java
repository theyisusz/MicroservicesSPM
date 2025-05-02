package co.edu.unicauca.studentmicroservice.infra.dto;

public class StudentDTO {
    private String cedula;
    private String codigo;
    private String email;
    private String nombre;
    private String telefono;

    public StudentDTO() {}

    public StudentDTO(String cedula, String codigo, String email, String nombre, String telefono) {
        this.cedula = cedula;
        this.codigo = codigo;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
