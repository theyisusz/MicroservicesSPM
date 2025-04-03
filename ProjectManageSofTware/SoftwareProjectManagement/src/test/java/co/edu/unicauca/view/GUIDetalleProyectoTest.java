package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.Postulation;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.Student;
import co.edu.unicauca.domain.services.PostulationService;
import co.edu.unicauca.domain.services.StudentService;
import co.edu.unicauca.infra.IFrameEventListener;
import co.edu.unicauca.infra.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.sql.Timestamp;

import static org.mockito.Mockito.*;

class GUIDetalleProyectoTest {

    private GUIDetalleProyecto guiDetalleProyecto;

    @Mock
    private PostulationService mockPostulaciones;

    @Mock
    private StudentService mockServiceStudent;

    @Mock
    private IFrameEventListener mockListener;

    @Mock
    private Project mockProyecto;

    @Mock
    private JTextField txtNombre, txtEmpresa, txtFecha, txtTiempoMaximo, txtPresupuesto;

    @Mock
    private JTextArea txtAreaObjetivos, txtAreaResumen, txtAreaDescripcion;

    private String username = "testUser";
    private Student mockStudent;

    @BeforeEach
    void setUp() {
         Messages.setEnabled(false);
        MockitoAnnotations.openMocks(this);
        guiDetalleProyecto = new GUIDetalleProyecto(new JFrame(), mockListener, mockProyecto, username, mockPostulaciones, mockServiceStudent);
        
        // Inyectar los mocks de los campos de texto
        guiDetalleProyecto.setTxtNombre(txtNombre);
        guiDetalleProyecto.setTxtEmpresa(txtEmpresa);
        guiDetalleProyecto.setTxtFecha(txtFecha);
        guiDetalleProyecto.setTxtTiempoMaximo(txtTiempoMaximo);
        guiDetalleProyecto.setTxtPresupuesto(txtPresupuesto);
        guiDetalleProyecto.setTxtAreaObjetivos(txtAreaObjetivos);
        guiDetalleProyecto.setTxtAreaResumen(txtAreaResumen);
        guiDetalleProyecto.setTxtAreaDescripcion(txtAreaDescripcion);

        mockStudent = new Student("Test Nombre", "12345678", "testCodigo", "test@email.com", "1234567890");
    }

    @Test
    void testGUIDetalleProyectoInitialization() {
        assert guiDetalleProyecto != null;
    }


    @Test
    void testBotonVolverAction() {
        JButton btnVolver = guiDetalleProyecto.btnVolver;
        assert btnVolver != null;

        btnVolver.doClick();

        assert !guiDetalleProyecto.isVisible();
    }

    @Test
    void testInicializarDatos() {
        when(mockProyecto.getNombre()).thenReturn("Proyecto Test");
        when(mockProyecto.getNombreEmpresa()).thenReturn("Empresa Test");
        when(mockProyecto.getFechaEntregadaEsperada()).thenReturn("2025-12-31");
        when(mockProyecto.getTiempoMaximo()).thenReturn("6 meses");
        when(mockProyecto.getPresupuesto()).thenReturn("5000 USD");
        when(mockProyecto.getObjetivo()).thenReturn("Desarrollar un sistema de gestión");
        when(mockProyecto.getResumen()).thenReturn("Resumen del proyecto");
        when(mockProyecto.getDescripcion()).thenReturn("Descripción detallada del proyecto");

        guiDetalleProyecto.inicializarDatos(mockProyecto);

        verify(txtNombre).setText("Proyecto Test");
        verify(txtEmpresa).setText("Empresa Test");
        verify(txtFecha).setText("2025-12-31");
        verify(txtTiempoMaximo).setText("6 meses");
        verify(txtPresupuesto).setText("5000 USD");
        verify(txtAreaObjetivos).setText("Desarrollar un sistema de gestión");
        verify(txtAreaResumen).setText("Resumen del proyecto");
        verify(txtAreaDescripcion).setText("Descripción detallada del proyecto");
    }
}