package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.CompanyService;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.domain.states.RechazadoState;
import co.edu.unicauca.domain.states.RecibidoState;
import co.edu.unicauca.infra.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GUIGestionSoftwareEmpresaTest {

    private GUIGestionSoftwareEmpresa gui;
    private CompanyService companyService;
    private ProjectService projectService;
    private User user;

    @BeforeEach
    void setUp() {
         Messages.setEnabled(false);
        // Crear mocks para los servicios
        companyService = Mockito.mock(CompanyService.class);
        projectService = Mockito.mock(ProjectService.class);

        // Crear un usuario de prueba
        user = new User();
        user.setUsuario("testUser");

        // Crear una instancia de la GUI con los mocks
        gui = new GUIGestionSoftwareEmpresa(companyService, projectService, user);
    }

    @Test
    void testActualizarTabla() {
        // Configurar el comportamiento esperado de los mocks
        Company company = new Company();
        company.setNit("123456789");
        when(companyService.obtenerCompanyPorUser("testUser")).thenReturn(company);

        Project project1 = new Project();
        project1.setNombre("Proyecto 1");
        project1.setNombreEmpresa("Empresa 1");
        project1.setFechaEntregadaEsperada("2025-01-01");
        project1.setEstado(new RecibidoState());

        Project project2 = new Project();
        project2.setNombre("Proyecto 2");
        project2.setNombreEmpresa("Empresa 2");
        project2.setFechaEntregadaEsperada("2025-02-01");
        project2.setEstado(new RechazadoState());

        List<Project> proyectos = Arrays.asList(project1, project2);
        when(projectService.obtenerProyectosPorNit("123456789")).thenReturn(proyectos);

        // Llamar al método que se va a probar
        gui.actualizarTabla();

        // Verificar que la tabla se actualizó correctamente
        JTable tblProyectos = gui.getTblProyectos();
        DefaultTableModel model = (DefaultTableModel) tblProyectos.getModel();

        assertEquals(2, model.getRowCount(), "La tabla debería tener 2 filas");

        assertEquals("Proyecto 1", model.getValueAt(0, 0), "El nombre del primer proyecto no coincide");
        assertEquals("Empresa 1", model.getValueAt(0, 1), "El nombre de la empresa del primer proyecto no coincide");
        assertEquals("2025-01-01", model.getValueAt(0, 2), "La fecha de entrega del primer proyecto no coincide");
        assertEquals("RECIBIDO", model.getValueAt(0, 3), "El estado del primer proyecto no coincide");

        assertEquals("Proyecto 2", model.getValueAt(1, 0), "El nombre del segundo proyecto no coincide");
        assertEquals("Empresa 2", model.getValueAt(1, 1), "El nombre de la empresa del segundo proyecto no coincide");
        assertEquals("2025-02-01", model.getValueAt(1, 2), "La fecha de entrega del segundo proyecto no coincide");
        assertEquals("RECHAZADO", model.getValueAt(1, 3), "El estado del segundo proyecto no coincide");
    }

    @Test
    void testActualizarTablaSinProyectos() {
        // Configurar el comportamiento esperado de los mocks
        Company company = new Company();
        company.setNit("123456789");
        when(companyService.obtenerCompanyPorUser("testUser")).thenReturn(company);

        when(projectService.obtenerProyectosPorNit("123456789")).thenReturn(null);

        // Llamar al método que se va a probar
        gui.actualizarTabla();

        // Verificar que la tabla está vacía
        JTable tblProyectos = gui.getTblProyectos();
        DefaultTableModel model = (DefaultTableModel) tblProyectos.getModel();

        assertEquals(0, model.getRowCount(), "La tabla debería estar vacía");
    }

 
}
