package pe.dev.nav.fulbitoapi.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.dev.nav.fulbitoapi.auth.dto.LoginRequest;
import pe.dev.nav.fulbitoapi.auth.dto.LoginResponse;
import pe.dev.nav.fulbitoapi.auth.jwt.JwtService;
import pe.dev.nav.fulbitoapi.user.domain.UserEntity;
import pe.dev.nav.fulbitoapi.user.repo.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest req) {
        UserEntity user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.isActive()) throw new IllegalArgumentException("User inactive");

        if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse(token, user.getRole().name());
    }
}