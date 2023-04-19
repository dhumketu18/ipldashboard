import React, { useState, useEffect } from 'react';
import {useParams, Link} from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';
import  './TeamPage.scss';
import { PieChart } from 'react-minimal-pie-chart';


export function TeamPage() {
  const[team, setTeam] = useState({matches : []});
  const{teamName} = useParams();
  const data = [
    { title: 'Wins', value: team.totalWin, color: '#E38627' },
    { title: 'Loss', value: team.totalMatches - team.totalWin, color: '#C13C37' }
  ];

  useEffect(() => {
    const fetchTeam = async () => {
      const url = process.env.REACT_APP_API_URL + '/team/' + teamName;
      const response = await fetch(url);
      const data = await response.json();
      setTeam(data);
    };
    fetchTeam();

  }, [teamName]);

  return (
    <div className="TeamPage">
    <div className="Team-name-section">
    <h1 className="Team-name"> {team.teamName} </h1>
    </div>
    <div className="Win-loss-section">
    <PieChart
      data={data}
    />
    </div>
    <div className = "Match-detail-section">
    <MatchDetailCard match = {team.matches[0]} teamName = {teamName}/>
    </div>
    {team.matches.slice(1).map(match => <MatchSmallCard key = {match.id} match = {match} teamName = {teamName}/>)}
    <div className = "More-Section">
    <Link to={`/teams/${teamName}/matches?year=${process.env.REACT_APP_END_YEAR}`}>More > </Link>
    </div>
    </div>
  );
}