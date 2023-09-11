import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";
import axios from "axios";
//import { Form, Button, Container, Alert } from 'react-bootstrap';

const AddStudent = () => {
  const baseURL = "http://localhost:9080/saveStudent";
  const navigate = useNavigate();
  const [regno, setRegno] = useState('');
  const [name, setName] = useState('');
  const[department,setDepartment]=useState('');

  const regnoChangeHandler = (event) => {
    //alert(event.target.value);
    setRegno(event.target.value);
  };

  const nameChangeHandler = (event) => {
    setName(event.target.value);
  };

  const departmentChangeHandler = (event) => {
    setDepartment(event.target.value);
  };


  const submitActionHandler = (event) => {
    event.preventDefault();
    axios
      .post(baseURL, {
        regno: regno,
        name: name,
        department:department
      })
      .then((response) => {
        alert(response.data.name);
        alert("Student "+ name +" added!");
        //navigate("/account");
      }).catch(error => {
        alert("error==="+error);
      });

  };

  const cancelHandler = () =>{
    //reset the values of input fields
    setName('');
    setRegno('');
    setDepartment('');
   // navigate("/read");

  }
    return(
      
      
      <form onSubmit={submitActionHandler}>
        
            Reg no:
            <input type="text" value={regno} onChange={regnoChangeHandler} placeholder="Enter Register number" required/><br></br>
        
            Name :
        <input type="text" value={name} onChange={nameChangeHandler} placeholder="Enter name" required/><br></br>
        Department:
        <input type="text" value={department} onChange={departmentChangeHandler} placeholder="Enter deprtment" required/><br></br>
        <br></br>
        <button type='submit'>Add Student</button>
        &nbsp;&nbsp;&nbsp;
        <button type='reset' onClick={()=>cancelHandler()}>Cancel</button>
      </form>

    
    
    );
}
export default AddStudent;
