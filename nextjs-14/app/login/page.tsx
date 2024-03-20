'use client'
import { useState } from "react";
import axios from 'axios';
import Link from "next/link";
const SERVER = 'http://localhost:8080'

export default function Login() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleUserName = (e: any) => {
        setUsername(e.target.value)
    }
    const handlePassword = (e: any) => {
        setPassword(e.target.value)
    }

    const handleSubmit = () => {
        alert('리퀘스트가 가져가는 아이디 : ' + username)
        const url = `${SERVER}/login`
        const data = { username, password }
        const config = {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                Authorization: `Bearer blah ~`,
                "Access-Control-Allow-Origin": "*",
            }
        }
        axios.post(url, data, config)
            .then(res => {
                alert("리스펀스가 가져온 이름 : " + JSON.stringify(res.data))
            })
    }


    return (<>
        <h2>환영합니다.</h2>
        <h4>아이디</h4>
        <input type="text" onChange={handleUserName}></input>
        <h4>비밀번호</h4>
        <input type="text" onChange={handlePassword}></input>
        <br></br>
        <button onClick={handleSubmit}>로그인</button>
        <br></br><br></br>
        <Link href={"http://localhost:3000/join"} >회원가입 </Link>
    </>)
}