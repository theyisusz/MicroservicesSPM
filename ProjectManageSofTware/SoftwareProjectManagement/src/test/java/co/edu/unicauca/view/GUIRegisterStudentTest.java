/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.view;

import co.edu.unicauca.domain.services.StudentService;
import co.edu.unicauca.infra.Messages;
import org.assertj.core.api.Assertions;
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
import org.mockito.MockitoAnnotations;

/**
 *
 * @author evers
 */
public class GUIRegisterStudentTest {
    
   @Mock
    private StudentService mockService;

    @InjectMocks
    private GUIRegisterStudent mockGUI; // Usamos @InjectMocks para inyectar los mocks

    @BeforeEach
    public void setUp() {
        
        // Desactivar los mensajes globalmente
        Messages.setEnabled(false);

        MockitoAnnotations.openMocks(this);
       
    }

    @ParameterizedTest
    @CsvSource({
        "juan, 100121, 104622, mera@domain.com, 3215369024, true",
        "'', 100121, 104622, mera@domain.com, 3215369024, false",
        "juan, '', 104622, mera@domain.com, 3215369024, false",
        "juan, 100121, '', mera@domain.com, 3215369024, false",
        "juan, 100121, 104622, '', 3215369024, false",
        "juan, 100121, 104622, mera@domain.com, '', false",
    })
    void testValidarFormularios(String nombre, String cedula, String codigo, String email, String telefono,  boolean expected) {

        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            // Simulamos que showMessageDialog no haga nada
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);

            // Ejecutamos la validación
            boolean result = mockGUI.validarFormulario(nombre,cedula,codigo,email,telefono);

            // Verificamos el resultado esperado
            Assertions.assertThat(result).isEqualTo(expected);
        }
    }


}
