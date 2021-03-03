import { useState, useEffect } from "react";
import Map from "./Map";
import axios from "axios";
import { Button } from "@material-ui/core";

const Maps = () => {
  const [mapa, setMap] = useState([]);
  const [isClicked, setClicked] = useState(false);

  useEffect(() => {
    axios
      .get("game/map/")
      .then(function (response) {
        console.log(response.data);
        setMap(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  function componentDidMount() {
    console.log("Maps", "componentDidMount");
  }

  const moveClickHandler = () => {
    axios.post("game/1/move").then(
      (response) => {
        onClickHandler();
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  };

  const showInfoHandler = () => {
    setClicked(!isClicked);
  };

  const onClickHandler = () => {
    axios
      .get("game/map/")
      .then(function (response) {
        console.log(response.data);
        setMap(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="text-center mt-5 mb-5">
      {isClicked === true ? <Map mapa={mapa} /> : console.log("Map hidden.")}

      {componentDidMount()}
      <div className="row">
        <div className="col-md-4">
          <Button variant="contained" color="primary" onClick={showInfoHandler}>
            {isClicked === true ? "Hide Info" : "Show info"}
          </Button>
        </div>
        <div className="col-md-4">
          <Button variant="contained" onClick={onClickHandler}>
            Refresh
          </Button>
        </div>
        <div className="col-md-4">
          <Button
            variant="contained"
            color="secondary"
            onClick={moveClickHandler}
          >
            Move
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Maps;
