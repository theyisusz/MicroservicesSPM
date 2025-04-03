package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.PostulationService;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.domain.services.StudentService;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.infra.renderButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GUIGestionSottwareStudentTest {

    @Mock
    private ProjectService projectServiceMock;

    @Mock
    private StudentService studentServiceMock;

    @Mock
    private PostulationService postulationServiceMock;

    private GUIGestionSottwareStudent gui;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Desactivar los mensajes globalmente
        Messages.setEnabled(false);

        // Crear un usuario de prueba
        User testUser = new User();
        testUser.setUsuario("testStudent");

        // Inicializar la GUI con los servicios mockeados
        gui = new GUIGestionSottwareStudent(projectServiceMock, testUser, studentServiceMock, postulationServiceMock);
    }

    @Test
    void actualizarProyectos_DebeCargarProyectosEnTabla() {
        // Mockear la lista de proyectos
        List<Project> proyectosMock = List.of(
                new Project("123456789", "Proyecto 1", "Resumen breve", "Descripción larga",
                        "Objetivo del proyecto", "6 meses", "10000", "2025-06-01")
        );

        // Configurar el mock para projectService
        when(projectServiceMock.obtenerProyectos()).thenReturn(proyectosMock);

        // Ejecutar la función
        gui.actualizarProyectos();

        // Verificar que la tabla tiene el proyecto
        JTable tblProyectos = gui.getTblProyectos();
        assertNotNull(tblProyectos, "La tabla de proyectos no debe ser nula");
        assertEquals(1, tblProyectos.getRowCount(), "La tabla debe tener 1 fila");

        // Verificar que las columnas se configuraron correctamente
        TableColumn detallesColumn = tblProyectos.getColumnModel().getColumn(5);
        assertNotNull(detallesColumn.getCellRenderer(), "El renderizador de la columna 'Ver Detalles' no debe ser nulo");
        assertTrue(detallesColumn.getCellRenderer() instanceof renderButton, "El renderizador debe ser una instancia de renderButton");

        TableColumn escondidoColumn = tblProyectos.getColumnModel().getColumn(6);
        assertEquals(0, escondidoColumn.getWidth(), "El ancho de la columna 'escondido' debe ser 0");
        assertEquals(0, escondidoColumn.getPreferredWidth(), "El ancho preferido de la columna 'escondido' debe ser 0");

        // Verificar contenido de la fila
        assertEquals(1, tblProyectos.getValueAt(0, 0), "El número de fila debe ser 1");
        assertEquals("Proyecto 1", tblProyectos.getValueAt(0, 3), "El nombre del proyecto debe ser 'Proyecto 1'");
        assertEquals("Resumen breve", tblProyectos.getValueAt(0, 4), "El resumen debe ser 'Resumen breve'");
        assertEquals("Ver mas", tblProyectos.getValueAt(0, 5), "La columna de detalles debe mostrar 'Ver mas'");
    }
}
