package pe.dev.nav.fulbitoapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test-local")
class TeamIntegrationTest extends LocalMysqlTestConfig{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateTeam() throws Exception {
        mockMvc.perform(post("/api/teams/league/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "name": "Unión Fulbito",
                  "shortName": "UF",
                  "colorPrimary": "#1e40af",
                  "colorSecondary": "#22c55e"
                }
                """))
                .andExpect(status().isOk());
    }
}
