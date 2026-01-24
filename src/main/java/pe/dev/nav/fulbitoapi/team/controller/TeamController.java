package pe.dev.nav.fulbitoapi.team.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.dev.nav.fulbitoapi.team.dto.TeamRequest;
import pe.dev.nav.fulbitoapi.team.dto.TeamResponse;
import pe.dev.nav.fulbitoapi.team.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/league/{leagueId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponse> create(
            @PathVariable Long leagueId,
            @RequestBody TeamRequest request) {
        return ResponseEntity.ok(teamService.create(leagueId, request));
    }

    @GetMapping("/league/{leagueId}")
    public ResponseEntity<List<TeamResponse>> list(
            @PathVariable Long leagueId) {
        return ResponseEntity.ok(teamService.listByLeague(leagueId));
    }
}

