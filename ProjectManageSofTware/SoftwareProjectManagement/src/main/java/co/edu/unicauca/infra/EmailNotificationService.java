/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;


import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.interfaces.NotificationService;
import com.google.protobuf.Message;
import com.mysql.cj.Session;
import java.net.PasswordAuthentication;
import java.util.Properties;

/**
 *
 * @author Yisus
 */
public class EmailNotificationService implements NotificationService {

    private User receptor;
    private User emisor;
    private String mensaje;

    public EmailNotificationService(User receptor, User emisor, String mensaje) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.mensaje = mensaje;
    }

    @Override
    public void notificarCambioEstado(User receptor, User emisor, String mensaje) {
       
    }

}
