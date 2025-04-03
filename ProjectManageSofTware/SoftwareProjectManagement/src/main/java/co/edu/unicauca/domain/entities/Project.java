package co.edu.unicauca.domain.entities;

import co.edu.unicauca.domain.states.RecibidoState;
import co.edu.unicauca.interfaces.ProjectState;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class Project {

    private String id;
    private String nitEmpresa;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String TiempoMaximo;
    private String presupuesto;
    private String FechaEntregadaEsperada;
    private ProjectState estado;
    private String nombreEmpresa;

    public Project(){
        
    }
    public Project(String id, String nitEmpresa, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada, ProjectState estado, String nombreEmpresa) {
        this.id = id;
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.TiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.nombreEmpresa = nombreEmpresa;
        this.estado = new RecibidoState(); // Estado inicial
    }
    public Project(String nitEmpresa, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada) {
        this.id = id;
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.TiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.nombreEmpresa = nombreEmpresa;
        this.estado = new RecibidoState(); // Estado inicial
    }
    

    public void avanzarEstado() {
        this.estado.avanzarEstado(this);
    }

    public void NoAvanzaEstado() {
        this.estado.NoAvanzaEstado(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTiempoMaximo() {
        return TiempoMaximo;
    }

    public void setTiempoMaximo(String TiempoMaximo) {
        this.TiempoMaximo = TiempoMaximo;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getFechaEntregadaEsperada() {
        return FechaEntregadaEsperada;
    }

    public void setFechaEntregadaEsperada(String FechaEntregadaEsperada) {
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
    }

    public ProjectState getEstado() {
        return estado;
    }

    public String getEstadoString() {
        return estado.getEstado().toString();
    }

    public void setEstado(ProjectState estado) {
        this.estado = estado;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

}
