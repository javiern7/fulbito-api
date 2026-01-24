package pe.dev.nav.fulbitoapi.team.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.dev.nav.fulbitoapi.league.domain.LeagueEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "league_id")
    private LeagueEntity league;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "short_name", length = 20)
    private String shortName;

    @Column(name = "color_primary", length = 20)
    private String colorPrimary;

    @Column(name = "color_secondary", length = 20)
    private String colorSecondary;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TeamLogo> logos = new ArrayList<>();
}
