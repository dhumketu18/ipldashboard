import React, { useState, useEffect } from 'react';
import {TeamTitle} from '../components/TeamTitle';
import './HomePage.scss';

export function HomePage() {
  const[teams, setTeams] = useState([]);

  useEffect(() => {
    const fetchTeam = async () => {
      const response = await fetch(process.env.REACT_APP_API_URL + '/team');
      const data = await response.json();
      setTeams(data);
    };
    fetchTeam();

  }, []);

  return (
    <div className="HomePage">
    <div className="app-header">
    <h1> IPL DASHBOARD </h1>
    </div>

    <div className = "team-grid">
    {teams.map(team  => <TeamTitle key = {team.id} team = {team}/>)}
    </div>
    </div>
  );
}