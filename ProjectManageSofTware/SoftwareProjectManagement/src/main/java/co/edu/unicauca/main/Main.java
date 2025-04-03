package co.edu.unicauca.main;

import co.edu.unicauca.domain.services.UserService;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.view.FrameFactory;
import co.edu.unicauca.view.GUILogin;

import javax.swing.JFrame;

public class Main {

    private static GUILogin loginInstance = null;

    public static void mostrarLogin() {
        if (loginInstance == null) {  // Solo se crea una vez
            IRepository userRepository = Factory.getInstance().getRepository("usuario");
            UserService service = new UserService(userRepository);
            IFrameFactory frameFactory = new FrameFactory();
            loginInstance = new GUILogin(service, frameFactory);
            loginInstance.setSize(450, 380);
            loginInstance.setLocationRelativeTo(null);
        }
        loginInstance.setVisible(true);
        loginInstance.setExtendedState(JFrame.NORMAL);
    }

    public static void main(String[] args) {
        mostrarLogin();
    }
}
