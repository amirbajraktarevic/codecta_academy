import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Game from "./components/Game";
import About from "./components/About";
import NavBar from "./components/NavBar";
import Home from "./components/Home";

function App() {
  return (
    <div>
      <Router>
        <NavBar />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/about" component={About} />
          <Route path="/game" component={Game} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
