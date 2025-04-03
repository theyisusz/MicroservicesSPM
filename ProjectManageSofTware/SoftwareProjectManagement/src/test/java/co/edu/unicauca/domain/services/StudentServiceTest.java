package co.edu.unicauca.domain.services;

import co.edu.unicauca.infra.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StudentService
 */
public class StudentServiceTest {

    private StudentService studentService;
    private IRepository repositoryMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(IRepository.class);
        studentService = new StudentService(repositoryMock);
    }

    

    @ParameterizedTest
    @CsvSource({
        "Juan, juan@email.com, 123456789, 12345678, 20230001, true",
        "Juan1, juan@email.com, 123456789, 12345678, 20230001, false",
        "Juan, juanemail.com, 123456789, 12345678, 20230001, false",
        "Juan, juan@email.com, 12345a, 12345678, 20230001, false",
        "Juan, juan@email.com, 123456789, abcde, 20230001, false",
        "Juan, juan@email.com, 123456789, 12345678, abc, false"
    })
    void testValidarCampos(String nombre, String email, String telefono, String cedula, String codigo, boolean expected) {
        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);
            boolean result = studentService.validarcampos(nombre, email, telefono, cedula, codigo);
            assertEquals(expected, result);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "juan@email.com, true",
        "juanemail.com, false",
        "juan@.com, true",
        "juan@domain, true",
        "@domain.com, false",
        "juan@domain.com, true"
    })
    void testValidarEmail(String email, boolean expected) {
        boolean result = studentService.validarEmail(email);
        assertEquals(expected, result);
    }
}
