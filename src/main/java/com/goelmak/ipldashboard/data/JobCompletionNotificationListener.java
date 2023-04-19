package com.goelmak.ipldashboard.data;

import com.goelmak.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }


        HashMap<String, Team> hm = new HashMap<>();
        entityManager.createQuery("Select m.team1, count(*) from Match m group by m.team1", Object[].class)
                .getResultList()
                .stream()
                .map(e -> new Team((String) e[0], (long) e[1]))
                .forEach(team -> hm.put(team.getTeamName(), team));

        entityManager.createQuery("Select m.team2, count(*) from Match m group by m.team2", Object[].class)
                .getResultList()
                .stream()
                .forEach(e -> {
                    Team team = hm.get((String) e[0]);
                    team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                });

        entityManager.createQuery("Select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                .getResultList()
                .stream()
                .forEach(e -> {
                    Team team = hm.get((String) e[0]);
                    if (team != null)
                    team.setTotalWin((long) e[1]);
                });
        hm.values().forEach(e -> entityManager.persist(e));
    }
}