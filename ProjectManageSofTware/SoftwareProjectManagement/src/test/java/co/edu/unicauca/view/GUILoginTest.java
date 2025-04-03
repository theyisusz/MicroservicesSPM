package co.edu.unicauca.view;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.UserService;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.IFrameFactory;
import javax.swing.JFrame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

public class GUILoginTest {
    
    @Mock
    private UserService mockUserService;
    
    @Mock
    private IFrameFactory mockFrameFactory;
    
    @Mock
    private JFrame mockJFrame;
    
    @InjectMocks
    private GUILogin guiLogin;
    
    @BeforeEach
    public void setUp() {
        Messages.setEnabled(false);
        MockitoAnnotations.openMocks(this);
        guiLogin = new GUILogin(mockUserService, mockFrameFactory);
    }
    
    @ParameterizedTest
    @CsvSource({
        "usuario1, password1, true",  // Credenciales correctas
   
    })
    void testLogin(String usuario, String contrasenia, boolean expected) {
        User mockUser = mock(User.class);
        when(mockUser.getContrasenia()).thenReturn(contrasenia);
        when(mockUserService.login(anyString(), anyString()))
                .thenAnswer(invocation -> {
                    String userParam = invocation.getArgument(0);
                    String passParam = invocation.getArgument(1);
                    return (userParam.equals("usuario1") && passParam.equals("password1")) ? mockUser : null;
                });
        
        try (MockedStatic<Messages> mockedMessages = mockStatic(Messages.class)) {
            mockedMessages.when(() -> Messages.showMessageDialog(anyString(), anyString())).thenAnswer(invocation -> null);
            
            boolean result = guiLogin.verifyPassword(contrasenia, mockUser.getContrasenia());
            Assertions.assertThat(result).isEqualTo(expected);
        }
    }
}