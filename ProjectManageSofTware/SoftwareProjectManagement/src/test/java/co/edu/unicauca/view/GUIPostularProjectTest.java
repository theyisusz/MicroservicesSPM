/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.CompanyService;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.infra.IFrameEventListener;
import co.edu.unicauca.infra.Messages;
import javax.swing.JFrame;
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
public class GUIPostularProjectTest {
    
   
    @Mock
    private JFrame mockJFrame;
    
    @Mock
    private ProjectService mockProjectService;
    
    @Mock
    private IFrameEventListener mockIFrameEventListener ;
            
    @Mock
    private User mockUser;
            
    @Mock       
    private CompanyService mockCompanyService;
  
    
    
    @Mock
    private GUIPostularProject mockGUI; // Usamos @InjectMocks para inyectar los mocks

    @BeforeEach
    public void setUp() {
        // Desactivar los mensajes globalmente
        Messages.setEnabled(false);

        MockitoAnnotations.openMocks(this);
        mockGUI = new GUIPostularProject(new JFrame(),mockProjectService,mockIFrameEventListener,mockUser,mockCompanyService);
    }   
    
        
    @ParameterizedTest
    @CsvSource({
    // ✅ Caso válido (todos los datos correctos)
    "juan, Proyecto innovador, Descripción del proyecto, Mejorar procesos, 12 meses, 50000, 2025-12-31, true",

    // ❌ Campos vacíos individuales (cada uno debe retornar false)
    "'', Proyecto innovador, Descripción del proyecto, Mejorar procesos, 12 meses, 50000, 2025-12-31, false",
    "juan, '', Descripción del proyecto, Mejorar procesos, 12 meses, 50000, 2025-12-31, false",
    "juan, Proyecto innovador, '', Mejorar procesos, 12 meses, 50000, 2025-12-31, false",
    "juan, Proyecto innovador, Descripción del proyecto, '', 12 meses, 50000, 2025-12-31, false",
    "juan, Proyecto innovador, Descripción del proyecto, Mejorar procesos, '', 50000, 2025-12-31, false",
    "juan, Proyecto innovador, Descripción del proyecto, Mejorar procesos, 12 meses, '', 2025-12-31, false",
    "juan, Proyecto innovador, Descripción del proyecto, Mejorar procesos, 12 meses, 50000, '', false",

    // ❌ Todos los campos vacíos
    "'', '', '', '', '', '', '', false",

    // ❌ Casos con placeholders ("project name", "overView", etc.)
    "'project name', Proyecto innovador, Descripción del proyecto, Mejorar procesos, 12 meses, 50000, 2025-12-31, false",
    })
    void testvalidarCamposVacios(String nombre, String resumen, String descripcion, String objetivo, String tiempoMaximo, String presupuesto, String fechaEntregaEsperada,boolean expected) {

        try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
            // Simulamos que showMessageDialog no haga nada
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);

            // Ejecutamos la validación
            boolean result = mockGUI.validarCamposVacios(nombre, resumen, descripcion, objetivo, tiempoMaximo, presupuesto, fechaEntregaEsperada);

            // Verificamos el resultado esperado
            Assertions.assertThat(result).isEqualTo(expected);
        }
    }
    
    
    @ParameterizedTest
@CsvSource({
    // ✅ Caso válido con datos grandes
    "'juan', 'Proyecto extremadamente innovador con un impacto global en la industria tecnológica y académica', " +
    "'Este proyecto tiene como objetivo revolucionar la forma en que se manejan los datos en sistemas distribuidos y mejorar la eficiencia del procesamiento paralelo en grandes volúmenes de información.', " +
    "'Automatizar completamente el flujo de trabajo empresarial a través de la integración de inteligencia artificial y sistemas de aprendizaje profundo.', " +
    "'120 meses', '1000000000', '2099-12-31', true",

    // ❌ Casos con campos vacíos
    "'', 'Proyecto innovador', 'Descripción extensa del proyecto...', 'Objetivo de gran impacto...', '120 meses', '1000000', '2099-12-31', false",
    "'juan','', 'Descripción extensa del proyecto...', 'Objetivo de gran impacto...', '120 meses', '1000000', '2099-12-31', false",
    "'juan', 'Proyecto innovador', '', 'Objetivo de gran impacto...', '120 meses', '1000000', '2099-12-31', false",
    "'juan', 'Proyecto innovador', 'Descripción extensa del proyecto...', '', '120 meses', '1000000', '2099-12-31', false",


    // ❌ Campos con caracteres inválidos o placeholders
    "'project name', 'Resumen largo...', 'Descripción extensa...', 'Objetivo válido...', '120 meses', '1000000', '2099-12-31', false",
})
void testvalidarLongitudCampos(String nombre, String resumen, String descripcion, String objetivo, 
                               String tiempoMaximo, String presupuesto, String fechaEntregaEsperada, boolean expected) {

    try (MockedStatic<Messages> mockedMessages = Mockito.mockStatic(Messages.class)) {
        mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);

        boolean result = mockGUI.validarCamposVacios(nombre, resumen, descripcion, objetivo, tiempoMaximo, presupuesto, fechaEntregaEsperada);

        Assertions.assertThat(result).isEqualTo(expected);
    }
}
    
    
    
    
    
    
   
    
}
