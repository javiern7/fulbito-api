package pe.dev.nav.fulbitoapi.league.service;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pe.dev.nav.fulbitoapi.league.domain.LeagueEntity;
import pe.dev.nav.fulbitoapi.league.dto.LeagueCreateRequest;
import pe.dev.nav.fulbitoapi.league.dto.LeagueResponse;
import pe.dev.nav.fulbitoapi.league.dto.LeagueUpdateRequest;
import pe.dev.nav.fulbitoapi.league.repo.LeagueRepository;

import java.util.List;

@Service
public class LeagueService {

    private final LeagueRepository repo;

    public LeagueService(LeagueRepository repo) {
        this.repo = repo;
    }

    public List<LeagueResponse> listActive() {
        return repo.findByActiveTrueOrderByIdDesc()
                .stream()
                .map(e -> new LeagueResponse(e.getId(), e.getName(), e.getCity(), e.isActive()))
                .toList();
    }

    public LeagueResponse getActive(Long id) {
        LeagueEntity e = repo.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("League not found"));
        return new LeagueResponse(e.getId(), e.getName(), e.getCity(), e.isActive());
    }

    @Transactional
    public LeagueResponse create(LeagueCreateRequest req) {
        LeagueEntity e = new LeagueEntity();
        e.setName(req.getName());
        e.setCity(req.getCity());
        e.setActive(true);
        repo.save(e);
        return new LeagueResponse(e.getId(), e.getName(), e.getCity(), e.isActive());
    }

    @Transactional
    public LeagueResponse update(Long id, LeagueUpdateRequest req) {
        LeagueEntity e = repo.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("League not found"));
        e.setName(req.getName());
        e.setCity(req.getCity());
        repo.save(e);
        return new LeagueResponse(e.getId(), e.getName(), e.getCity(), e.isActive());
    }

    @Transactional
    public void deactivate(Long id) {
        LeagueEntity e = repo.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("League not found"));
        e.setActive(false);
        repo.save(e);
    }
}
