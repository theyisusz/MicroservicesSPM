package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Postulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for PostulationService
 */
public class PostulationServiceTest {

    private PostulationService postulationService;
    private IRepository repositoryMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(IRepository.class);
        postulationService = new PostulationService(repositoryMock);
    }

    @Test
    public void testSavePostulation() {
        Postulation postulation = new Postulation();
        when(repositoryMock.save(postulation)).thenReturn(true);

        boolean result = postulationService.savePostulation(postulation);

        assertTrue(result);
        verify(repositoryMock, times(1)).save(postulation);
    }

   

    @Test
    public void testExistePostulacion_CuandoExiste() {
        Postulation postulation = new Postulation();
        postulation.setCodProject("1234");
        postulation.setCodStudent("5678");
        when(repositoryMock.found(postulation)).thenReturn(postulation);

        boolean result = postulationService.existePostulacion(postulation);

        assertFalse(result);
        verify(repositoryMock, times(1)).found(postulation);
    }
}
