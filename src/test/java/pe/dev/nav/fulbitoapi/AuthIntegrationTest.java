package pe.dev.nav.fulbitoapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.dev.nav.fulbitoapi.user.domain.Role;
import pe.dev.nav.fulbitoapi.user.domain.UserEntity;
import pe.dev.nav.fulbitoapi.user.repo.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthIntegrationTest extends LocalMysqlTestConfig {

    @LocalServerPort
    int port;

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    private String base() { return "http://localhost:" + port; }

    @Test
    void login_and_me_ok() {
        UserEntity u = new UserEntity();
        u.setEmail("it_admin@fulbito.com");
        u.setPasswordHash(encoder.encode("Admin123*"));
        u.setRole(Role.ADMIN);
        u.setActive(true);
        userRepo.save(u);

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<Map> loginRes = rest.postForEntity(
                base() + "/api/auth/login",
                Map.of("email","it_admin@fulbito.com","password","Admin123*"),
                Map.class
        );

        assertThat(loginRes.getStatusCode().value()).isEqualTo(200);
        String token = (String) loginRes.getBody().get("token");
        assertThat(token).isNotBlank();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        ResponseEntity<Map> meRes = rest.exchange(
                base() + "/api/auth/me",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        );

        assertThat(meRes.getStatusCode().value()).isEqualTo(200);
        assertThat(meRes.getBody().get("email")).isEqualTo("it_admin@fulbito.com");
        assertThat(meRes.getBody().get("role")).isEqualTo("ADMIN");
    }
}
