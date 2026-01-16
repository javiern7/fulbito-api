package pe.dev.nav.fulbitoapi.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VersionController {

    @Value("${app.version:1.0.0}")
    private String version;

    @GetMapping("/version")
    public Map<String, String> version() {
        return Map.of("name", "fulbito-api", "version", version);
    }
}
