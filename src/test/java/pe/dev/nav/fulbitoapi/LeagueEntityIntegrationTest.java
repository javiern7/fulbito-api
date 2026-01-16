package pe.dev.nav.fulbitoapi;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.dev.nav.fulbitoapi.user.domain.Role;
import pe.dev.nav.fulbitoapi.user.domain.UserEntity;
import pe.dev.nav.fulbitoapi.user.repo.UserRepository;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LeagueEntityIntegrationTest extends LocalMysqlTestConfig {

    @LocalServerPort
    int port;

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    private final TestRestTemplate rest = new TestRestTemplate();

    private String base() {
        return "http://localhost:" + port;
    }

    private String seedAndLogin(String email, String pass, Role role) {
        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(pass));
        u.setRole(role);
        u.setActive(true);
        userRepo.save(u);

        ResponseEntity<Map> loginRes = rest.postForEntity(
                base() + "/api/auth/login",
                Map.of("email", email, "password", pass),
                Map.class
        );

        return (String) loginRes.getBody().get("token");
    }

    @Test
    void admin_can_create_user_cannot_create_but_can_read() {
        String adminToken = seedAndLogin("admin_it@fulbito.com", "Admin123*", Role.ADMIN);
        String userToken = seedAndLogin("user_it@fulbito.com", "User123*", Role.USER);

        // ADMIN create
        HttpHeaders hAdmin = new HttpHeaders();
        hAdmin.setBearerAuth(adminToken);
        hAdmin.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Map> created = rest.exchange(
                base() + "/api/leagues",
                HttpMethod.POST,
                new HttpEntity<>(Map.of("name", "Liga Centro", "city", "Lima"), hAdmin),
                Map.class
        );
        assertThat(created.getStatusCode().value()).isEqualTo(201);

        // USER cannot create
        HttpHeaders hUser = new HttpHeaders();
        hUser.setBearerAuth(userToken);
        hUser.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> forbidden = rest.exchange(
                base() + "/api/leagues",
                HttpMethod.POST,
                new HttpEntity<>(Map.of("name", "Liga Norte", "city", "Piura"), hUser),
                String.class
        );
        assertThat(forbidden.getStatusCode().value()).isEqualTo(403);

        // USER can read list
        ResponseEntity<String> listOk = rest.exchange(
                base() + "/api/leagues",
                HttpMethod.GET,
                new HttpEntity<>(hUser),
                String.class
        );
        assertThat(listOk.getStatusCode().value()).isEqualTo(200);
    }
}
