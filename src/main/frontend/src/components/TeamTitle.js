import React from 'react';
import {Link} from 'react-router-dom';
import './TeamTitle.scss';

export function TeamTitle({team}) {

if (!team) {
return;
}
  return (
    <div className = "TeamTitle">
    <Link to = {`/teams/${team.teamName}`}>
       <h1>{team.teamName}</h1>
    </Link>
    </div>
  );
}