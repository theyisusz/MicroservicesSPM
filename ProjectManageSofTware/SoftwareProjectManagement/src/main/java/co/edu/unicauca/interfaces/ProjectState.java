/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Project;
import java.util.List;

/**
 *
 * @author Yisus
 */
public interface ProjectState {
    ProjectState avanzarEstado(Project proyecto);
    ProjectState NoAvanzaEstado(Project proyecto);
    String getEstado();
    }

