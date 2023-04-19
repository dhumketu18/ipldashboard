package com.goelmak.ipldashboard.data;

import com.goelmak.ipldashboard.model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchItemProcessor implements ItemProcessor<MatchInput, Match> {

        @Override
        public Match process(final MatchInput matchInput) throws Exception {

            final Match match = new Match();

            final String team1;
            final String team2;


            if ("bat".equals(matchInput.getToss_decision())) {
                team1 = matchInput.getToss_winner();
                team2 = (matchInput.getToss_winner().equals(matchInput.getTeam1())) ? matchInput.getTeam2() : matchInput.getTeam1();
            } else {
                team2 = matchInput.getToss_winner();
                team1 = (matchInput.getToss_winner().equals(matchInput.getTeam1())) ? matchInput.getTeam2() : matchInput.getTeam1();
            }

            match.setTeam1(team1);
            match.setTeam2(team2);

            match.setUmpire1(matchInput.getUmpire1());
            match.setUmpire2(matchInput.getUmpire2());

            match.setVenue(matchInput.getVenue());
            match.setCity(matchInput.getCity());

            match.setResult(matchInput.getResult());
            match.setMatchWinner(matchInput.getWinner());
            match.setResultMargin(matchInput.getResult_margin());


            match.setDate(LocalDate.parse(matchInput.getDate()));
            match.setId(Long.parseLong(matchInput.getId()));

            match.setPlayerOfMatch(matchInput.getPlayer_of_match());
            match.setTossWinner(matchInput.getToss_winner());
            match.setTossDecision(matchInput.getToss_decision());

            return match;
        }

    }

