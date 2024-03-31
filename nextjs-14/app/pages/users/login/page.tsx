"use client";
import { useState } from "react";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { NextPage } from "next";
const SERVER = "http://localhost:8080";

const LoginPage:NextPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUserName = (e: any) => {
    setUsername(e.target.value);
  };
  const handlePassword = (e: any) => {
    setPassword(e.target.value);
  };

  const router = useRouter();

  const handleSubmit = () => {
    alert("리퀘스트가 가져가는 아이디 : " + username);
    const url = `${SERVER}/api/auth`;
    const data = { username, password };
    const config = {
      headers: {
        "Cache-Control": "no-cache",
        "Content-Type": "application/json",
        Authorization: `Bearer blah ~`,
        "Access-Control-Allow-Origin": "*",
      },
    };
    axios
      .post(url, data, config) // post한 data는 RequestBody이다.
      .then((res) => {
        //alert("리스펀스가 가져온 이름 : " + JSON.stringify(res.data)) // res.data에서 .data는 ResponseBody이다.
        const message = res.data.message;
        alert(message);
        if (message === "SUCCESS") {
          router.push("/pages/boards/articles/writer");
        } else if (message === "FAIL") {
        } else if (message === "WRONG_PASSWORD") {
        } else {
          alert("지정되지 않은 값");
        }
      });
  };

  return (
    <>
      <h2>환영합니다.</h2>
      <h4>아이디</h4>
      <input type="text" onChange={handleUserName}></input>
      <h4>비밀번호</h4>
      <input type="text" onChange={handlePassword}></input>
      <br></br>
      <button onClick={handleSubmit}>로그인</button>
      <br></br>
      <br></br>
      <Link href={"http://localhost:3000/pages/users/join"}>회원가입 </Link>
    </>
  );
}

export default LoginPage