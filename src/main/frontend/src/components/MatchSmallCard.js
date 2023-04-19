import React from 'react';
import {Link} from 'react-router-dom';

export function MatchSmallCard({match, teamName}) {

if (!match || !teamName) {
return;
}
const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
const isWon =  teamName === match.matchWinner;
  return (
    <div className={isWon ? 'MatchSmallCard won-card' : 'MatchSmallCard loss-card'}>
       <h3>
       VS <Link to = {`/teams/${otherTeam}`}>{otherTeam}</Link>
       </h3>
       <h3>
        {` ${match.matchWinner} won by ${match.resultMargin} ${match.result}`}
       </h3>
    </div>
  );
}