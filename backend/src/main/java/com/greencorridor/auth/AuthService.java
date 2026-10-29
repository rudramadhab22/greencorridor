package com.greencorridor.auth;

import com.greencorridor.auth.dto.LoginRequest;
import com.greencorridor.auth.dto.LoginResponse;
import com.greencorridor.auth.dto.UserResponse;
import com.greencorridor.common.exception.ApiException;
import com.greencorridor.common.exception.ErrorCode;
import com.greencorridor.common.security.JwtProperties;
import com.greencorridor.common.security.JwtService;
import com.greencorridor.common.security.UserPrincipal;
import com.greencorridor.user.domain.User;
import com.greencorridor.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            JwtProperties jwtProperties,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "User not found"));

        String token = jwtService.generateAccessToken(principal);
        return new LoginResponse(
                token,
                "Bearer",
                jwtProperties.getAccessTokenExpiryMinutes(),
                UserResponse.from(user)
        );
    }

    @Transactional(readOnly = true)
    public UserResponse currentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "User not found"));
        return UserResponse.from(user);
    }
}
