import React, { Component, useState } from "react";

import moment from "moment";
import Form from "react-bootstrap/Form";
import { Check, X } from "react-bootstrap-icons";

const TodoForm = ({ addTodo }) => {
  const [values, setValues] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!values) return;
    addTodo(values);
    setValues("");
  };
  return (
    <Form onSubmit={handleSubmit} className="form-bottom">
      <Form.Control
        type="text"
        name="text"
        value={values}
        placeholder="Type your task here"
        onChange={(e) => setValues(e.target.value)}
      ></Form.Control>
    </Form>
  );
};

export default function Demo() {
  const [todos, setTodos] = useState([
    { text: "First Line", isComplete: false, dateSubmit: Date },
    { text: "Second Line", isComplete: false, dateSubmit: Date },
    { text: "Third Line", isComplete: false, dateSubmit: Date },
  ]);
  const date = moment(new Date()).format("DD-MM-yyyy");
  console.log(date);
  const addTodo = (text) => {
    const newTodos = [...todos, { text, isComplete: false, dateSubmit: date }];
    console.log(newTodos);
    setTodos(newTodos);
  };

  const completeTodo = (index) => {
    const newTodos = [...todos];
    newTodos[index].isComplete = true;
    setTodos(newTodos);
  };

  const deleteTodo = (index) => {
    const newTodos = [...todos];
    newTodos.splice(index, 1);
    setTodos(newTodos);
  };

  return (
    <div className="main-box">
      <div>Welcome to Demo</div>
      <div className="todo-list">
        {todos.map((todo, index) => (
          <Todo
            index={index}
            todo={todo}
            key={index}
            completeTodo={completeTodo}
            deleteTodo={deleteTodo}
          />
        ))}
        <TodoForm addTodo={addTodo} />
      </div>
    </div>
  );
}

const Todo = ({ todo, index, completeTodo, deleteTodo }) => (
  <div
    className="todo"
    style={{ textDecoration: todo.isComplete ? "line-through" : "" }}
  >
    {todo.text}

    <Check onClick={() => completeTodo(index)} />

    <X onClick={() => deleteTodo(index)} />
  </div>
);
