package com.greencorridor.auth;

import com.greencorridor.auth.dto.LoginRequest;
import com.greencorridor.auth.dto.LoginResponse;
import com.greencorridor.common.security.JwtProperties;
import com.greencorridor.common.security.JwtService;
import com.greencorridor.common.security.UserPrincipal;
import com.greencorridor.user.domain.Role;
import com.greencorridor.user.domain.RoleName;
import com.greencorridor.user.domain.User;
import com.greencorridor.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserRepository userRepository;

    private JwtProperties jwtProperties;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        jwtProperties = new JwtProperties();
        jwtProperties.setAccessTokenExpiryMinutes(60);
        authService = new AuthService(authenticationManager, jwtService, jwtProperties, userRepository);
    }

    @Test
    void loginReturnsTokenAndUser() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@greencorridor.local");
        user.setPasswordHash("hash");
        user.setFullName("System Administrator");
        Role role = new Role();
        role.setName(RoleName.ADMIN);
        user.setRoles(Set.of(role));

        UserPrincipal principal = new UserPrincipal(user);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        when(jwtService.generateAccessToken(principal)).thenReturn("test-token");

        LoginResponse response = authService.login(new LoginRequest("admin", "Admin@123"));

        assertEquals("test-token", response.accessToken());
        assertEquals("Bearer", response.tokenType());
        assertEquals(60, response.expiresInMinutes());
        assertNotNull(response.user());
        assertEquals("admin", response.user().username());
        assertEquals(Set.of("ADMIN"), response.user().roles());
    }
}
