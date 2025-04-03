/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.infra.Subject;
import co.edu.unicauca.interfaces.ProjectState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yisus
 */
public class ProjectService extends Subject {

    private IProjectRepository repository;

    public ProjectService() {

    }

    public ProjectService(IRepository repository) {
        this.repository = (IProjectRepository) repository;
    }
    // Método para agregar observadores

    // Método para obtener proyectos
    public List<Project> obtenerProyectos() {

        List<Object> objects = repository.list();
        List<Project> projects = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Project) {
                projects.add((Project) obj);
            }
        }
        notificarObservadores(projects);
        // Devuelves la lista de Project como List<Object>
        return new ArrayList<>(projects);
    }

    public List<Project> obtenerProyectosPorNit(String nit) {

        List<Object> objects = repository.getProjectsNit(nit);
        List<Project> projects = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Project) {
                projects.add((Project) obj);
            }
        }
        // Devuelves la lista de Project como List<Object>
        return new ArrayList<>(projects);

    }

    public boolean saveProject(Project project) {
        return repository.save(project);

    }
    
   public Project buscarProyectoPorNombre(Project project){
       return (Project) repository.found(project.getNombre()); 
   }
   public boolean cambiarEstado(Project proyecto, ProjectState nuevoEstado) {

        if (!validarCambioEstado(proyecto, nuevoEstado)) {
            return false;
        }

        proyecto.setEstado(nuevoEstado);
        repository.actualizarEstado(proyecto);
        System.out.println(repository.actualizarEstado(proyecto));
        notificarCambioEstado(proyecto);

        return true;
    }

    private boolean validarCambioEstado(Project proyecto, ProjectState nuevoEstado) {
        ProjectState estadoActual = proyecto.getEstado();

        if (estadoActual.getEstado().equals("RECIBIDO") && nuevoEstado.getEstado().equals("CERRADO")) {
            return false;
        }

        return true;
    }

    public Project getProject(Object idProject) {
        return (Project) repository.found(idProject);
    }

    private void notificarCambioEstado(Project proyecto) {

    }
}
