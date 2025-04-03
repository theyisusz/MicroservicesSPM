package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.infra.Messages;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GUIGestionSofwareCoordinationTest {
    
    private GUIGestionSofwareCoordination gui;
    private ProjectService projectServiceMock;
    private User usuarioMock;
    private List<Project> proyectosMock;
    
    @BeforeEach
    void setUp() {
        
        // Desactivar los mensajes globalmente
        Messages.setEnabled(false);

        projectServiceMock = mock(ProjectService.class);
        usuarioMock = mock(User.class);
        proyectosMock = new ArrayList<>();
        when(projectServiceMock.obtenerProyectos()).thenReturn(proyectosMock);
        
        gui = new GUIGestionSofwareCoordination(projectServiceMock, usuarioMock);
    }
    
    @Test
    void testActualizarTablaP() {
        Project proyecto1 = new Project("123456789", "Proyecto 1", "Resumen breve", "Descripción larga",
                        "Objetivo del proyecto", "6 meses", "10000", "2025-06-01");
        Project proyecto2 = new Project("12123111", "Proyecto 2", "Resumen breve", "Descripción larga",
                        "Objetivo del proyecto", "9 meses", "200000", "2025-08-01");
        proyectosMock.add(proyecto1);
        proyectosMock.add(proyecto2);
        gui.actualizarTablaP(proyectosMock);
        
        DefaultTableModel model = (DefaultTableModel) gui.getjTable1().getModel();
        assertEquals(2, model.getRowCount());
        assertEquals("Proyecto 1", model.getValueAt(0, 0));
        assertEquals("Proyecto 2", model.getValueAt(1, 0));
    }
    
    @Test
    void testConstructorInitializesComponents() {
        assertNotNull(gui);
        assertNotNull(gui.projectService);
        assertNotNull(gui.usuario);
        assertNotNull(gui.proyectos);
    }
}
