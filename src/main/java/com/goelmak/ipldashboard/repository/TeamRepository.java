package com.goelmak.ipldashboard.repository;

import com.goelmak.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team getByTeamName(String teamName);


}
