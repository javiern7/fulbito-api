package pe.dev.nav.fulbitoapi.league.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dev.nav.fulbitoapi.league.dto.LeagueCreateRequest;
import pe.dev.nav.fulbitoapi.league.dto.LeagueResponse;
import pe.dev.nav.fulbitoapi.league.dto.LeagueUpdateRequest;
import pe.dev.nav.fulbitoapi.league.service.LeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueService service;

    public LeagueController(LeagueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeagueResponse>> list() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getActive(id));
    }

    @PostMapping
    public ResponseEntity<LeagueResponse> create(@Valid @RequestBody LeagueCreateRequest req) {
        return ResponseEntity.status(201).body(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeagueResponse> update(@PathVariable Long id, @Valid @RequestBody LeagueUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
