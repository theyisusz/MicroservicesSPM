package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.ProjectState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GUIGestionSofwareCoordinationProjectTest {

    private GUIGestionSofwareCoordinationProject gui;
    private ProjectService projectServiceMock;
    private Project projectMock;
    private User userMock;

    @BeforeEach
    void setUp() {
         Messages.setEnabled(false);
        // Crear mocks de las dependencias
        projectServiceMock = Mockito.mock(ProjectService.class);
        projectMock = Mockito.mock(Project.class);
        userMock = Mockito.mock(User.class);

        // Configurar el comportamiento de los mocks
        when(userMock.getUsuario()).thenReturn("coordinadorTest");
        when(projectMock.getEstadoString()).thenReturn("RECIBIDO");

        // Configurar el estado actual del proyecto
        ProjectState estadoActualMock = Mockito.mock(ProjectState.class);
        when(projectMock.getEstado()).thenReturn(estadoActualMock);

        // Crear una instancia de la GUI con los mocks
        gui = new GUIGestionSofwareCoordinationProject(projectServiceMock, projectMock, userMock);
    }

    @Test
    void testCambiarEstado_NoDisponible() {
        // Configurar el comportamiento del mock de Project
        when(projectMock.getEstadoString()).thenReturn("CERRADO");

        // Mockear Messages.showMessageDialog para evitar que aparezca la ventana
        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString()))
                    .thenAnswer(invocation -> null);

            // Simular la selección de un nuevo estado en el JComboBox
            gui.getCbxestados().addItem("No hay opciones disponibles");
            gui.getCbxestados().setSelectedItem("No hay opciones disponibles");

            // Ejecutar el método que queremos probar
            gui.cambiarEstado();

            // Verificar que se mostró el mensaje de error
            mockedMessages.verify(() -> Messages.showMessageDialog("No se puede cambiar a este Estado", "Error"));
        }
    }
}
