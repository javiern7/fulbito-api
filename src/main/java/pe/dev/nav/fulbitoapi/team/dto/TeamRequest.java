package pe.dev.nav.fulbitoapi.team.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamRequest {
    private String name;
    private String shortName;
    private String colorPrimary;
    private String colorSecondary;
}
