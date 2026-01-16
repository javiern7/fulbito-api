package pe.dev.nav.fulbitoapi.auth.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import pe.dev.nav.fulbitoapi.auth.dto.LoginRequest;
import pe.dev.nav.fulbitoapi.auth.dto.LoginResponse;
import pe.dev.nav.fulbitoapi.auth.dto.MeResponse;
import pe.dev.nav.fulbitoapi.auth.jwt.UserPrincipal;
import pe.dev.nav.fulbitoapi.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(Authentication authentication) {
        UserPrincipal p = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(new MeResponse(p.getId(), p.getUsername(), p.getRole().name()));
    }
}
