package co.edu.unicauca.infra;

import javax.swing.JOptionPane;

/**
 *
 * @author Everson
 */
public class Messages {
    private static boolean enabled = true; // Variable para activar/desactivar los mensajes

    public static void setEnabled(boolean state) {
        enabled = state;
    }

    public static void showMessageDialog(String message, String title) {
        if (enabled) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static int showConfirmDialog(String message, String title) {
        if (enabled) {
            return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
        return JOptionPane.CLOSED_OPTION; // Retorna una opción cerrada si está desactivado
    }
}