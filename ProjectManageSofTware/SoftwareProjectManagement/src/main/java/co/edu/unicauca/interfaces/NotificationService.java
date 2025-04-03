/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;

/**
 *
 * @author Yisus
 */
public interface NotificationService {
     void notificarCambioEstado(User receptor,User emisor,String mensaje);
}
