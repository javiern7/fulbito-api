package pe.dev.nav.fulbitoapi.league.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dev.nav.fulbitoapi.league.domain.LeagueEntity;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
    List<LeagueEntity> findByActiveTrueOrderByIdDesc();
    Optional<LeagueEntity> findByIdAndActiveTrue(Long id);
}
