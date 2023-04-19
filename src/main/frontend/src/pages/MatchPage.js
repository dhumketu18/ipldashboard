import React, { useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import queryString from 'query-string';
import './MatchPage.scss';
import {YearSelector} from '../components/YearSelector';


export function MatchPage() {
  const[matches, setMatches] = useState([]);
  const { search } = useLocation();
  const { year } = queryString.parse(search);
  const{teamName} = useParams();

     useEffect(() => {
        const fetchTeam = async () => {
          const response = await fetch(process.env.REACT_APP_API_URL + '/team/' + teamName + '/matches?year=' + year);
          const data = await response.json();
          setMatches(data);
        };
        fetchTeam();
      }, [year, teamName]);

  return (
    <div className="MatchPage">
    <div className="year-selector">
    <h3> select year </h3>
    <YearSelector year = {year} teamName = {teamName} />
     </div>
    <div>
    <h1 className="page-heading"> {teamName} matches in {year} </h1>
    {
    matches.map(match => <MatchDetailCard key={match.id} match = {match} teamName = {teamName}/>)
    }
    </div>
    </div>
  );
}