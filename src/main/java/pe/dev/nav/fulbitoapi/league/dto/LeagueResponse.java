package pe.dev.nav.fulbitoapi.league.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeagueResponse {
    private Long id;
    private String name;
    private String city;
    private boolean active;
}
