import React from 'react';
import {Link} from 'react-router-dom';
import './MatchDetailCard.scss';
import '../App.scss';

export function MatchDetailCard({match, teamName}) {
if (!match || !teamName) {
return;
}
const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
const isWon =  teamName === match.matchWinner;

  return (
    <div  className = {isWon ? 'MatchDetailCard won-card' : 'MatchDetailCard loss-card'}>
    <div className="LatestMatch">
    <h3> Latest Match </h3>
    <span className = "Vs"> VS </span>
    <h1>
    <Link to = {`/teams/${otherTeam}`}>{otherTeam}</Link>
    </h1>
    <h2 className = "MatchDate">
    {match.date}
    </h2>
    <h3 className = "MatchCity">
    {`at ${match.city}`}
    </h3>
    <h3 className = "MatchResult">
    {` ${match.matchWinner} won by ${match.resultMargin} ${match.result}`}
    </h3>
    </div>
    <div className="AdditionalDetails">
    <h3>First Innings</h3>
    <p>{match.team1}</p>
    <h3>Second Innings</h3>
    <p>{match.team2}</p>
    <h3>MAN OF THE MATCH</h3>
    <p>{match.playerOfMatch}</p>
    <h3> UMPIRES </h3>
    <p>{match.umpire1}, {match.umpire2}</p>
    </div>
    </div>
  );
}