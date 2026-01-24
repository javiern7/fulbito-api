package pe.dev.nav.fulbitoapi.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dev.nav.fulbitoapi.team.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByLeagueId(Long leagueId);
}
