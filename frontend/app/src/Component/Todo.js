import React, { useState, useEffect } from "react";
import AuthService from "../Service/auth.service";
import authHeader from "../Service/auth-header";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { Check, X } from "react-bootstrap-icons";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { FaSignOutAlt } from "react-icons/fa";

const TodoForm = ({ addTodo }) => {
  const [value, setValue] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!value) return;
    addTodo(value);

    setValue("");
  };

  return (
    <Form onSubmit={handleSubmit} className="form-bottom">
      <Form.Control
        type="text"
        placeholder="Type your task here"
        value={value}
        name="text"
        onChange={(e) => {
          setValue(e.target.value);
        }}
      ></Form.Control>
    </Form>
  );
};

export default function Todo(props) {
  const [todos, setTodos] = useState([{ text: "", isComplete: null }]);
  const [show, setShow] = useState(false);
  const [loading, setLoading] = useState(true);
  const [update, setUpdate] = useState("");

  const API_URL = `http://localhost:8080/user/`;
  const currentUser = AuthService.getCurrentUser();

  const addTodo = (text) => {
    const newTodo = { text: text };
    console.log(newTodo);
    setUpdate("added");
    axios
      .post(API_URL + currentUser.userId + `/task`, newTodo, {
        headers: authHeader(),
      })
      .then((response) => console.log(response));
  };

  const handleClose = () => setShow(true);

  const completeTodo = (index) => {
    const id = todos[index].id;
    const taskId = { id: id };
    setUpdate("completed");
    axios
      .put(API_URL + currentUser.userId + `/task/` + id, taskId, {
        headers: authHeader(),
      })
      .then((response) => console.log(response));
  };

  const deleteTodo = (index) => {
    const id = todos[index].id;
    setUpdate("deleted");
    axios
      .delete(API_URL + currentUser.userId + `/task/` + id, {
        headers: authHeader(),
      })
      .then((response) => console.log(response));
  };

  const logout = () => {
    AuthService.logout();
    props.history.push("/");
  };

  useEffect(() => {
    if (!currentUser) {
      setShow(true);
      return;
    }
    setLoading(true);
    console.log(update);
    const fetchData = async () => {
      const response = await fetch(API_URL + currentUser.userId + `/tasks`, {
        headers: authHeader(),
      });
      setTodos(await response.json());
      setUpdate("");
      setLoading(false);
    };
    fetchData();
  }, [update]);

  if (!currentUser) {
    return (
      <div>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header>
            <Modal.Title>User not found</Modal.Title>
          </Modal.Header>
          <Modal.Body>Please Sign In before using Todo App</Modal.Body>
          <Modal.Footer>
            <Button href="/Login" variant="primary">
              Login
            </Button>
            <Button href="/Register" variant="primary">
              Register
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }

  return (
    <div>
      {loading ? (
        <div>Loading....</div>
      ) : (
        <div className="main-box">
          <div>
            Welcome {currentUser.username}
            <div style={{ float: "right", marginRight: "10px" }}>
              <FaSignOutAlt onClick={logout} />
            </div>
          </div>
          <div className="todo-list">
            {todos.map((todo, index) => (
              <TodoList
                key={index}
                todo={todo}
                index={index}
                completeTodo={completeTodo}
                deleteTodo={deleteTodo}
              />
            ))}
            <TodoForm addTodo={addTodo} />
          </div>
        </div>
      )}
    </div>

    // <div className="main-box">
    //   <div>
    //     Welcome {currentUser.username}
    //     <div style={{ float: "right", marginRight: "10px" }}>
    //       <FaSignOutAlt onClick={logout} />
    //     </div>
    //   </div>
    //   <div className="todo-list">
    //     {todos.map((todo, index) => (
    //       <TodoList
    //         key={index}
    //         todo={todo}
    //         index={index}
    //         completeTodo={completeTodo}
    //         deleteTodo={deleteTodo}
    //       />
    //     ))}
    //     <TodoForm addTodo={addTodo} />
    //   </div>
    // </div>
  );
}

const TodoList = ({ todo, index, completeTodo, deleteTodo }) => (
  <div
    className="todo"
    style={{ textDecoration: todo.complete ? "line-through" : "" }}
  >
    {todo.text}
    <Check onClick={() => completeTodo(index)} />
    <X onClick={() => deleteTodo(index)} />
  </div>
);
