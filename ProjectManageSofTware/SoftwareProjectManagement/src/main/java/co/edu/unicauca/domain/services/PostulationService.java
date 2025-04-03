/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Postulation;

/**
 *
 * @author RoLoNeGaTiVo
 */
public class PostulationService {

    private IRepository repository;

    public PostulationService(IRepository repository) {
        this.repository = repository;
    }

    public boolean savePostulation(Postulation postulation) {
        return repository.save((Postulation) postulation);
    }

    public boolean existePostulacion(Postulation postulation) {

        Postulation pos = new Postulation((Postulation) repository.found(postulation));

        if (pos.getCodProject() == null && pos.getCodStudent() == null) {
            return true;
        } else {
            return false;
        }
    }
}
