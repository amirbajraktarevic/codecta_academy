import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../App.css";

const NavBar = () => {
  return (
    <Nav className="bg-dark navbar">
      <Nav.Item>
        <Link className="navbar-link" to="/">
          HOME
        </Link>
        <Link className="navbar-link ml-5 " to="/about">
          ABOUT
        </Link>
      </Nav.Item>
    </Nav>
  );
};

export default NavBar;
