package pe.dev.nav.fulbitoapi.team.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamResponse {
    private Long id;
    private String name;
    private String shortName;
    private String colorPrimary;
    private String colorSecondary;
    private String logoUrl;
}
