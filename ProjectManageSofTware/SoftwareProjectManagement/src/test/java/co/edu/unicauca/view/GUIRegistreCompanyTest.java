/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.view;

import co.edu.unicauca.domain.services.CompanyService;
import co.edu.unicauca.infra.Messages;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat; // Importación correcta de AssertJ
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author evers
 */
public class GUIRegistreCompanyTest {

    @Mock
    private CompanyService mockService;

    @InjectMocks
    private GUIRegistreCompany mockGUI; // Usamos @InjectMocks para inyectar los mocks

    @BeforeEach
    public void setUp() {
        
        // Desactivar los mensajes globalmente
        Messages.setEnabled(false);

        MockitoAnnotations.openMocks(this);
       
    }

    @ParameterizedTest
    @CsvSource({
        "123, Empresa ABC, email@domain.com, Juan, Pérez, Gerente, 3123456789, TECH, true",
        "'' , Empresa ABC, email@domain.com, Juan, Pérez, Gerente, 3123456789, TECH, false",
        "123, '', email@domain.com, Juan, Pérez, Gerente, 3123456789, TECH, false",
        "123, Empresa ABC, '', Juan, Pérez, Gerente, 3123456789, TECH, false",
        "123456789012345678901234567890, Empresa ABC, email@domain.com,'', Pérez, Gerente, 3123456789, TECH, false",
        "123, Empresa ABC,email@domain.com, Juan, '', Gerente, 3123456789, TECH, false",
        "123456789012345678901234567890, Empresa ABC, email@domain.com,juan, Pérez, '', 3123456789, TECH, false",
        "123, Empresa ABC, email@domain.com, Juan, Pérez, Gerente, '', TECH, false",
        "123, Empresa ABC, email@domain.com, Juan, Pérez, Gerente, 3123456789, '', false"
    })
    void testValidarFormularios(String nit, String nombre, String email, String nombrecontacto, String apellido, String cargo, String telefono, String sector, boolean expected) {

        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            // Simulamos que showMessageDialog no haga nada
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);

            // Ejecutamos la validación
            boolean result = mockGUI.validarFormulario(nit, nombre, email, nombrecontacto, apellido, cargo, telefono, sector);

            // Verificamos el resultado esperado
            Assertions.assertThat(result).isEqualTo(expected);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "empresa, true",
        " mera, true",
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, true"
    })
    void testValidarNombre(String nombre, boolean expected) {
        boolean result = mockGUI.validarFormulario("123", nombre, "Pass@1", "Juan", "Mera", "TECH", "Gerente", "email@domain.com");
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "email@domain.com, true",
        "email.com, true",
        "@domain.com, true"
    })
    void testValidarEmail(String email, boolean expected) {
        boolean result = mockGUI.validarFormulario("123", "Nestle", "Pass@1", "Juan", "Mera", "TECH", "Gerente", email);
        Assertions.assertThat(result).isEqualTo(expected);
    }

}
