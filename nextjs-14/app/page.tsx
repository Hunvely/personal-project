'use client';
import { useState } from "react";
import axios from 'axios';
import Link from "next/link";
const SERVER = 'http://localhost:8080'

export default function Home() {

  const [name, setName] = useState('')

  const handleButton = () => {
    alert('리퀘스트가 가져가는 이름 : ' + name)
    const url = `${SERVER}/name`
    const data = { 'name': name }
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
  const handleChange = (e: any) => {
    setName(e.target.value)
  }

  return (<>
    <p>안녕하세요.</p> <p>리액트 오랜만입니다.</p>
    <h2>당신의 이름은 무엇인가요 ?</h2>
    <input type="text" onChange={handleChange}></input>
    <button onClick={handleButton}>입 력</button>
    <br></br><br></br>
    <Link href={"http://localhost:3000/login"} >로그인 </Link>
    <br></br><br></br>
    <Link href={"http://localhost:3000/join"} >회원가입 </Link>

  </>
  );
}