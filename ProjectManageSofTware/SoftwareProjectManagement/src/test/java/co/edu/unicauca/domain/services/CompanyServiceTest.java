package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.infra.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CompanyService
 */
public class CompanyServiceTest {

    private CompanyService companyService;
    private ICompanyRepository repositoryMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(ICompanyRepository.class);
        companyService = new CompanyService(repositoryMock);
    }

    @Test
    public void testListarEmpresas() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company());
        Mockito.when(repositoryMock.list()).thenReturn(new ArrayList<>(companies));

        List<Company> result = companyService.listarEmpresas();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @ParameterizedTest
    @CsvSource({
        "Empresa, empresa@email.com, Juan, Perez, Gerente,123456789, Tecnologia, true",
        "Empresa123, empresa@email.com, Juan, Perez, Gerente, 123456789, Tecnologia, false",
        "Empresa, empresaemail.com, Juan, Perez, Gerente, 123456789, Tecnologia, false",
        "Empresa, empresa@email.com, Juan, Perez, Gerente, abc123, Tecnologia, false",
        "Empresa, empresa@email.com, Juan, Perez, Gerente, 123456789, Tecno123, false"
    })
    void testValidarCampos(String nombre, String email, String nombrecontacto, String apellido, String cargo, String telefono, String sector, boolean expected) {
        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);
            boolean result = companyService.validarcampos(nombre, email, nombrecontacto, apellido, cargo, telefono, sector);
            assertEquals(expected, result);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "empresa@email.com, true",
        "empresaemail.com, false",
        "@domain.com, false",
        "empresa@domain.com, true"
    })
    void testValidarEmail(String email, boolean expected) {
        boolean result = companyService.validarEmail(email);
        assertEquals(expected, result);
    }
}
