"use client";
import { useState } from "react";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { NextPage } from "next";
const SERVER = "http://localhost:8080";

const WithdrawalPage: NextPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleUserName = (e: any) => {
        setUsername(e.target.value);
    };
    const handlePassword = (e: any) => {
        setPassword(e.target.value);
    };

    const data = { password };

    const router = useRouter();

    const handleSubmit = () => {
        const url = `${SERVER}/api/withdrawal/${username}?password=${password}`;
        axios
            .delete(url, { data: { password } })
            .then((res) => {
                const message = res.data.message;
                alert(message);
                if (message === "SUCCESS") {
                    // 탈퇴 처리 성공 시 동작
                    alert("SUCCESS")
                } else if (message === "FAIL") {
                    // 실패 시 동작
                    alert("FAIL")
                } else if (message === "WRONG_PASSWORD") {
                    // 잘못된 비밀번호일 경우 동작
                    alert("WRONG_PASSWORD")
                } else {
                    // 지정되지 않은 경우 동작
                    alert("지정되지 않은 값");
                }
            })
            .catch((error) => {
                // 에러 처리
                console.error("Error occurred:", error);
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
            <button onClick={handleSubmit}>탈퇴</button>
            <br></br>
            <br></br>
            <Link href={"http://localhost:3000/pages/users/join"}>회원가입 </Link>
        </>
    );
}

export default WithdrawalPage;
