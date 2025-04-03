package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.interfaces.ProjectState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for ProjectService
 */
public class ProjectServiceTest {

    private ProjectService projectService;
    private IProjectRepository repositoryMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(IProjectRepository.class);
        projectService = new ProjectService((IRepository) repositoryMock);
    }

    @Test
    public void testSaveProject() {
        Project project = new Project();
        when(repositoryMock.save(project)).thenReturn(true);

        boolean result = projectService.saveProject(project);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
        "RECIBIDO, CERRADO, false",
        "RECIBIDO, EN_PROCESO, true",
        "EN_PROCESO, FINALIZADO, true",
        "FINALIZADO, RECIBIDO, true"
    })
    void testCambiarEstado(String estadoActual, String nuevoEstado, boolean expected) {
        Project proyecto = Mockito.mock(Project.class);
        ProjectState actualState = Mockito.mock(ProjectState.class);
        ProjectState newState = Mockito.mock(ProjectState.class);

        when(proyecto.getEstado()).thenReturn(actualState);
        when(actualState.getEstado()).thenReturn(estadoActual);
        when(newState.getEstado()).thenReturn(nuevoEstado);
        when(repositoryMock.actualizarEstado(proyecto)).thenReturn(true);

        boolean result = projectService.cambiarEstado(proyecto, newState);
        assertEquals(expected, result);
    }
}
