'use client'
import { useState } from "react";
import axios from 'axios';
export default function Join() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [name,seteName] = useState('')
    const [phoneNumber,setPhoneNumber] = useState('')

    const joinClick = () => {
        alert('회원가입 완료')
    }

    const handleUsername = (e:any) => {
        setUsername(e.target.value)

    }

    const handlePassword = (e:any) => {
        setPassword(e.target.value)

    }

    const handleName = (e:any) => {
        seteName(e.target.value)
    }

    const handlePhoneNumber = (e:any) => {
setPhoneNumber(e.target.value )
    }

    return (<>
        <h2>회원가입</h2>
        <h4>ID</h4>
        <input type="text" onChange={handleUsername}></input>
        <h4>password</h4>
        <input type="text" onChange={handlePassword}></input>
        <h4>name</h4>
        <input type="text" onChange={handleName}></input>
        <h4>phoneNumber</h4>
        <input type="text" onChange={handlePhoneNumber}></input>
        <br></br><br></br>
        <button onClick={joinClick}>Join</button>

    </>)
}