package pe.dev.nav.fulbitoapi.team.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.dev.nav.fulbitoapi.league.domain.LeagueEntity;
import pe.dev.nav.fulbitoapi.league.repo.LeagueRepository;
import pe.dev.nav.fulbitoapi.team.dto.TeamRequest;
import pe.dev.nav.fulbitoapi.team.dto.TeamResponse;
import pe.dev.nav.fulbitoapi.team.entity.Team;
import pe.dev.nav.fulbitoapi.team.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public TeamResponse create(Long leagueId, TeamRequest request) {

        LeagueEntity league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        Team team = new Team();
        team.setLeague(league);
        team.setName(request.getName());
        team.setShortName(request.getShortName());
        team.setColorPrimary(request.getColorPrimary());
        team.setColorSecondary(request.getColorSecondary());

        Team saved = teamRepository.save(team);

        return TeamResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .shortName(saved.getShortName())
                .colorPrimary(saved.getColorPrimary())
                .colorSecondary(saved.getColorSecondary())
                .logoUrl(null)
                .build();
    }

    public List<TeamResponse> listByLeague(Long leagueId) {
        return teamRepository.findByLeagueId(leagueId)
                .stream()
                .map(team -> TeamResponse.builder()
                        .id(team.getId())
                        .name(team.getName())
                        .shortName(team.getShortName())
                        .colorPrimary(team.getColorPrimary())
                        .colorSecondary(team.getColorSecondary())
                        .logoUrl(null)
                        .build())
                .toList();
    }
}
