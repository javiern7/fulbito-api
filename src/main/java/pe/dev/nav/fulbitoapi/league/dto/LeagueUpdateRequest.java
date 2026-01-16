package pe.dev.nav.fulbitoapi.league.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueUpdateRequest {
    @NotBlank
    private String name;
    private String city;
}
