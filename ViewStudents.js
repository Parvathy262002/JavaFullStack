import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from "axios";
//import editIcon from "./../assets/edit.png";
//import deleteIcon from "./../assets/delete.JPG";
import "../App.css";


const ViewStudents = () => {

  const navigate = useNavigate();
  const baseURL = "http://localhost:9080/fetchStudents";
  const [students, setStudents] = useState([]);

  const setStudentData = () => {
    axios.get(baseURL ).then((response) => {
      setStudents(response.data);
    }).catch(error => {
      alert("Error Ocurred while loading data:" + error);
    });
  }

  useEffect(() => {
    setStudentData();
  }, []);

  return (
    <div class="card-body">
      <br>
      </br>
      <nav>
        <button
          className="btn btn-primary nav-item active"
          onClick={() => navigate("/create")}>
          Create New Student
        </button>
      </nav>


      <br></br>
      <div className="col-md-6">
        <h4>Students List</h4>

        <div class="container">
          <div class="row">
            <div class="col-12">
              <table class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th scope="col">Action</th>

                  </tr>
                </thead>
                <tbody>

                  {
                    
                    students.map((student, index) => (

                      <tr>
                        <th scope="row">{student.regno}</th>

                        <td>{student.name}</td>
                        <td>{student.department}</td>


                        <td >
                        

    <Link to={"/edit/" + student.regno}>Edit
                        </Link>


                       
                       

                      </td>
                          


                          

                        
                      </tr>

                    ))
                  }

                </tbody>
              </table>


              <select >
              {
              students.map((s, index) => (
              <option key={s.regno} value={s.regno}>{s.name}</option>
                   
                  
                ))
                  }
              </select>

            </div>
          </div>
        </div>
        
      </div>

    </div>

  );
}
export default ViewStudents;

