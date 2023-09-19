import React, { useEffect, useState } from 'react';
import {useParams, useNavigate} from "react-router-dom";
import axios from "axios";
//import { Form, Button, Container, Alert } from 'react-bootstrap';

const EditStudent = () => {

  const editURL = "http://localhost:9080/fetchStudentById/";
  const navigate = useNavigate();
  const param = useParams();
  const [regno, setRegno] = useState('');
  const [name, setName] = useState('');
  const [department, setDepartment] = useState('');
  const[id,setId]=useState(param.id);
useEffect(() => {

  axios.get(editURL+param.id).then((response) => {
    const studentData = response.data;
    setRegno(studentData.regno);
    setName(studentData.name);
    setDepartment(studentData.department);

  }).catch(error => { 
    alert("Error Ocurred getting student detail:"+ error);
  });
}, []);


  const nameChangeHandler = (event) => {
    setName(event.target.value);
  };

  const departmentChangeHandler = (event) => {
    setDepartment(event.target.value);
  };


  const submitActionHandler = (event) => {
    event.preventDefault();
    axios
      .put("http://localhost:9080/updateStudent/"+id, {
      regno: regno,
        name: name,
        department: department
      })
      .then((response) => {
        alert("Student"+ regno +" updated!");
        navigate('/viewStudents')

      }).catch(error => { 
        alert("Error Ocurred updating student:"+ error);
      });
      
  };

    return(  
     
      
      <form onSubmit={submitActionHandler} >
      
            Regno
            <input type="text" value={regno} readonly='readonly'/><br></br>
        
        
            Student Name
            <input type="text" value={name} onChange={nameChangeHandler} placeholder="Enter User Name" required/><br></br>
        
        
         Department
            <input type="text" value={department} onChange={departmentChangeHandler} placeholder="Enter Department" required/><br></br>
        
        <br></br>
        <button type='submit'>Update Student</button>
        &nbsp;&nbsp;&nbsp;
        <button type='submit' onClick={()=>navigate("/viewStudents")}>Cancel</button>
      </form>
      
    
    );
}
export default EditStudent;
