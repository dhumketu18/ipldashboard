import {TeamPage} from './pages/TeamPage'
import {HashRouter as Router, Route, Routes} from 'react-router-dom'
import {MatchPage} from './pages/MatchPage'
import {HomePage} from './pages/HomePage'
import './App.scss';

function App() {
  return (
  <div className = "App">
    <Router>
    <Routes>
      <Route exact path="/teams/:teamName" element = <TeamPage />/>
      <Route exact path = "/teams/:teamName/matches" element = <MatchPage />/>
      <Route exact path = "/" element = {<HomePage/>}/>
    </Routes>
    </Router>
    </div>
  );
}

export default App;
