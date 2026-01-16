package pe.dev.nav.fulbitoapi.user.seed;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.dev.nav.fulbitoapi.user.domain.Role;
import pe.dev.nav.fulbitoapi.user.domain.UserEntity;
import pe.dev.nav.fulbitoapi.user.repo.UserRepository;

import java.util.Optional;

@Component
@Profile("local")
public class LocalUsersSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public LocalUsersSeeder(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        createIfMissing("admin@fulbito.com", "Admin123*", Role.ADMIN);
        createIfMissing("user@fulbito.com", "User123*", Role.USER);
    }

    private void createIfMissing(String email, String rawPass, Role role) {
        Optional<UserEntity> found = userRepo.findByEmail(email);
        if (found.isPresent()) return;

        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(rawPass));
        u.setRole(role);
        u.setActive(true);
        userRepo.save(u);
    }
}