const Map = (props) => {
  return (
    <div className="container align-items-center mb-5">
      <h3>Game information:</h3>
      <hr />
      <h4>Current Dungeon information: </h4>
      <br />
      <h5>Current Dungeon name: {props.mapa.currentDungeon.name}</h5>
      <br />
      <h5>
        {" "}
        Current Dungeon monster HP: {
          props.mapa.currentDungeon.monsters[0].hp
        }{" "}
      </h5>
      <br />
      <h5>
        {" "}
        Current Dungeon monster damage:
        {props.mapa.currentDungeon.monsters[0].damage}
      </h5>
      <hr />
      <h4>Player information:</h4>
      <br />
      <h5>Player name: {props.mapa.currentPlayer.name}</h5>
      <br />
      <h5>Player HP: {props.mapa.currentPlayer.hp}</h5>
      <br />
      <h5>Player damage: {props.mapa.currentPlayer.damage}</h5>
    </div>
  );
};

export default Map;
