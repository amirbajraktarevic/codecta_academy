import { Button } from "@material-ui/core";
import "../App.css";

const Home = () => {
  return (
    <div className="container">
      <div className="text-center mt-5">
        <h1>React Learning</h1>
        <hr />
        <span>Welcome to my Demo!</span>
        <br />

        <div className="mt-5">
          <Button variant="contained" color="primary">
            <a className="navbar-link" href="/game">
              Go to Game info
            </a>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Home;
