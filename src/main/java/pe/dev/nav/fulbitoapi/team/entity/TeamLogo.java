package pe.dev.nav.fulbitoapi.team.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "team_logo")
@Getter
@Setter
public class TeamLogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "file_name", length = 150)
    private String fileName;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(name = "is_default")
    private Boolean isDefault = true;
}

