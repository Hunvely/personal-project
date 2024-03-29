'use client'

import { useState } from "react"
import axios from 'axios';
import Link from "next/link";
import { Button, Input } from "@mui/material";
import { PG } from "../redux/common/enums/PG";
import AxiosConfig from "../redux/common/configs/axios-config";
import { NextPage } from "next";

const SERVER = 'http://localhost:8080'
const HomePage:NextPage = () => {
  const [name, setName] = useState('')
  const handleChange = (e: any) => {
    setName(e.target.value)
  }

  const handleClick = () => {
    alert('리퀘스트가 가져가는 이름 : ' + name)
    const url = `${SERVER}/name`
    const data = { 'name': name }
    axios.post(url, data, AxiosConfig())
      .then(res => {
        alert('alert : ' + JSON.stringify(res.data))
        console.log('console : ' + JSON.stringify(res.data))
      }
      )

  }

  return (
  <div className='margincenter w-4/5 my-[30px] border-double border-4'>
    <div className="text-3xl font-bold underline text-center">Welcome to Hunvely world !!</div><br />
    <span className='text-red-500 mr-5'>이름 입력</span>
    <Input type="text" onChange={handleChange} className="mr-5" />
    <Button variant="outlined" onClick={handleClick}>전 송</Button>
    <br />
    <br />
    <Link href={`${PG.USER}/login`} className="underline" >로그인</Link><br />
    <Link href={`${PG.USER}/join`} className="underline">회원가입</Link><br />
    <Link href={`${PG.DEMO}/mui-demo`} className="underline">MUI 데모</Link><br />
    <Link href={`${PG.DEMO}/companies`} className="underline">회사</Link><br />
    <Link href={`${PG.DEMO}/counter`} className="underline">Counter</Link><br />
    <Link href={`${PG.DEMO}/redux-counter`} className="underline">Redux-Counter</Link><br />
    <Link href={`${PG.BOARD}/articles`} className="underline">전체 게시글</Link>



  </div>
  )


}

export default HomePage