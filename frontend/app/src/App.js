import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Home from "./Component/Home";
import Login from "./Component/Login";
import Register from "./Component/Register";
import Demo from "./Component/Demo";
import Todo from "./Component/Todo";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/demo" component={Demo} />
        <Route exact path="/todo" component={Todo} />
      </Switch>
    </Router>
  );
}

export default App;
