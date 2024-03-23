'use client';
import { useState } from "react";
import axios from 'axios';
import Link from "next/link";
import { useRouter } from "next/navigation";
import styles from './Join.module.css';
const SERVER = 'http://localhost:8080'
export default function Join() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [eMail, setEMail] = useState('')

    const handleChangeUsername = (e:any) => {
        setUsername(e.target.value)
    }
    const handleChangePassword = (e:any) => {
        setPassword(e.target.value)
    }
    const handleChangeName = (e:any) => {
        setName(e.target.value)
    }
    const handleChangePhone = (e:any) => {
        setPhoneNumber(e.target.value)
    }
    const handleChangeEMail = (e:any) => {
        setEMail(e.target.value)
    }

    const router = useRouter()
    
    const handleSubmit = () => {
        alert('리퀘스트가 가져가는 username : ' + username + ' password : ' + password + ' name : ' + name + ' phoneNumber : '  + phoneNumber + ' eMail : ' + eMail)
        const url = `${SERVER}/api/users`
        const data = {username, password, name, phoneNumber, eMail}
        const config = {
            headers:{
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                Authorization: `Bearer blah ~` ,
                "Access-Control-Allow-Origin": "*",
            }}
            axios.post(url, data, config)
            .then(res => {
              alert(JSON.stringify(res.data))
            router.push("/login")
      })
    }
    return(<>
<div className={styles.container}>
    <h1>Sign Up</h1>
    <p>Please fill in this form to create an account.</p>
    <hr/>
    <label htmlFor="username"><b>Username</b></label> <br/>
    <input type="text"  onChange = {handleChangeUsername} placeholder="Enter Username" name="username" required /> <br/><br></br>
    <label htmlFor="psw"><b>Password</b></label> <br/>
    <input type="password" onChange = {handleChangePassword} placeholder="Enter Password" name="psw" required /> <br/><br></br>
    <label htmlFor="name"><b>Name</b></label> <br/>
    <input type="name" onChange = {handleChangeName} placeholder="Enter Name" name="name" required /> <br/><br></br>
    <label htmlFor="phone"><b>PhoneNumber</b></label> <br/>
    <input type="phone" onChange = {handleChangePhone} placeholder="Enter PhoneNumber" name="phone" required /> <br/><br></br>
    <label htmlFor="email"><b>Email</b></label> <br/>
    <input type="text" onChange = {handleChangeEMail} placeholder="Enter Email" name="email" required /> <br/><br></br>
    <label>
      <input type="checkbox" checked={true} name="remember" style={{ marginBottom: "15px" }} /> Remember me
    </label>
    <p>By creating an account you agree to our <a href="#" style={{ color: "dodgerblue" }}>Terms & Privacy</a>.</p>
    <div className="clearfix">
      <button type="button" className="cancelbtn">Cancel</button>
      <button type="submit" onClick = {handleSubmit} className="signupbtn">Sign Up</button>
    </div>
</div>
    </>
    )
}