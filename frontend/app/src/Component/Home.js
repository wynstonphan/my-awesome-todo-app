import React, { Component } from "react";
import { Button } from "reactstrap";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="main-box">
      <p style={{ marginTop: "5%" }}>Welcome to Todo App</p>
      <div style={{ marginTop: "5%" }}>
        <Button
          block
          tag={Link}
          to="/Login"
          outline
          color="primary"
          size="lg"
          className="button"
        >
          Login
        </Button>
      </div>
      <div style={{ marginTop: "5%" }}>
        <Button
          tag={Link}
          to="/Register"
          outline
          color="primary"
          block
          size="lg"
          className="button"
        >
          Register
        </Button>
      </div>
      <div style={{ marginTop: "5%" }}>
        <Button
          tag={Link}
          to="/Demo"
          outline
          color="primary"
          className="button"
          size="lg"
          block
        >
          Demo
        </Button>
      </div>
    </div>
  );
}
