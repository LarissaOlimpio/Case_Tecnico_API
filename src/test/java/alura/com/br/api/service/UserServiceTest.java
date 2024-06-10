package alura.com.br.api.service;

import alura.com.br.api.domain.users.UserRepository;
import alura.com.br.api.domain.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateUser() {
        Users user = new Users();
        when(userRepository.save(any())).thenReturn(user);

        Users result = userService.createUser(user);

        verify(userRepository, times(1)).save(any());
        assertEquals(user, result);
    }
    @Test
    public void testGetUserByUsername() {
        Users user = new Users();
        String username = "testUser";
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Optional<Users> result = userService.getUserByUsername(username);

        verify(userRepository, times(1)).findByUsername(anyString());
        assertEquals(user, result.get());
    }
    @Test
    public void testGetUserByUsernameNotFound() {
        String username = "testUser";
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Optional<Users> result = userService.getUserByUsername(username);

        verify(userRepository, times(1)).findByUsername(anyString());
        assertEquals(Optional.empty(), result);
    }
}
