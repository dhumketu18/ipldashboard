package com.goelmak.ipldashboard.controller;

import com.goelmak.ipldashboard.model.Match;
import com.goelmak.ipldashboard.model.Team;
import com.goelmak.ipldashboard.repository.MatchRepository;
import com.goelmak.ipldashboard.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {
    private final Logger logger = LoggerFactory.getLogger(TeamController.class);
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }



    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatch(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        List<Match> matches = matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
        return matches;

    }
    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.getByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatches(teamName, 4));
        return team;
    }

    @GetMapping("/team")
    public Iterable<Team> getTeamName() {
        return this.teamRepository.findAll();
    }


}
