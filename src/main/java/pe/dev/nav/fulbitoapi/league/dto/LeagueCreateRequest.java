package pe.dev.nav.fulbitoapi.league.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueCreateRequest {
    @NotBlank
    private String name;
    private String city;
}
