package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.infra.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FrameFactoryTest {

    @Mock
    private Factory mockFactory;

    @Mock
    private IRepository mockRepositoryStudent;

    @Mock
    private IRepository mockRepositoryProject;

    @Mock
    private IRepository mockRepositoryPostulation;

    @Mock
    private IRepository mockRepositoryCompany;

    private FrameFactory frameFactory;

    @BeforeEach
    void setUp() {
         Messages.setEnabled(false);
        MockitoAnnotations.openMocks(this);
        frameFactory = new FrameFactory();

        // Configurar el comportamiento de Factory para devolver los repositorios mockeados
        when(mockFactory.getRepository("student")).thenReturn(mockRepositoryStudent);
        when(mockFactory.getRepository("project")).thenReturn(mockRepositoryProject);
        when(mockFactory.getRepository("postulation")).thenReturn(mockRepositoryPostulation);
        when(mockFactory.getRepository("company")).thenReturn(mockRepositoryCompany);


    }

    @Test
    void testCreateFrameForStudent() {
        User studentUser = new User();
        studentUser.setRol("ESTUDIANTE");

        JFrame frame = frameFactory.createFrame(studentUser);

        assertNotNull(frame);
        assertTrue(frame instanceof GUIGestionSottwareStudent);
    }

    @Test
    void testCreateFrameForCoordinator() {
        User coordinatorUser = new User();
        coordinatorUser.setRol("COORDINADOR");

        JFrame frame = frameFactory.createFrame(coordinatorUser);

        assertNotNull(frame);
        assertTrue(frame instanceof GUIGestionSofwareCoordination);
    }

    @Test
    void testCreateFrameForCompany() {
        User companyUser = new User();
        companyUser.setRol("EMPRESA");

        JFrame frame = frameFactory.createFrame(companyUser);

        assertNotNull(frame);
        assertTrue(frame instanceof GUIGestionSoftwareEmpresa);
    }

    @Test
    void testCreateFrameForUnknownRole() {
        User unknownUser = new User();
        unknownUser.setRol("UNKNOWN_ROLE");

        JFrame frame = frameFactory.createFrame(unknownUser);

        assertNull(frame);
    }
}