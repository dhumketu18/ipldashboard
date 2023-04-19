import React from 'react';
import {Link} from 'react-router-dom';
import './YearSelector.scss'
export function YearSelector({teamName, year})  {
let years = [];
const startYear = parseInt(process.env.REACT_APP_START_YEAR);
const lastYear = parseInt(process.env.REACT_APP_END_YEAR);
for (let i = startYear; i <= lastYear; i++) {
  years.push(i);
}
  return (
  <div className = "YearSelector">
 {years.map((year) => (
   <li key={teamName.id}>
     <Link to={`/teams/${teamName}/matches?year=${year}`}>{year}</Link>
   </li>
 ))}
  </div>
  );
}