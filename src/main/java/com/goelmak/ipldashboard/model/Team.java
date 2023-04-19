package com.goelmak.ipldashboard.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long totalMatches;
    private long totalWin;
    private String teamName;

    @Transient
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public long getId() {
        return id;
    }

    public Team() {
    }

    public long getTotalMatches() {
        return totalMatches;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", totalMatches=" + totalMatches +
                ", totalWin=" + totalWin +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    public long getTotalWin() {
        return totalWin;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public void setTotalWin(long totalWin) {
        this.totalWin = totalWin;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, long totalMatches) {
        this.totalMatches = totalMatches;
        this.teamName = teamName;
    }
}
