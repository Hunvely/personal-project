'use client'

import { useState } from "react"
import axios from 'axios';
import Link from "next/link";
import { Box, Button, Input } from "@mui/material";
import { PG } from "./components/common/enums/PG";
import AxiosConfig from "./components/common/configs/axios-config";
import { NextPage } from "next";
import SearchIcon from '@mui/icons-material/Search';
import PermIdentityIcon from '@mui/icons-material/PermIdentity';
import styles from "./main.module.css"
import MenuIcon from '@mui/icons-material/Menu';
import { API } from "./components/common/enums/API";

const SERVER = 'http://localhost:8080'
const HomePage: NextPage = () => {
  const [name, setName] = useState('')
  const handleChange = (e: any) => {
    setName(e.target.value)
  }

  const handleClick = () => {
    alert('리퀘스트가 가져가는 이름 : ' + name)
    const url = `${API.SERVER}/name`
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
        alert('alert : ' + JSON.stringify(res.data))
        console.log('console : ' + JSON.stringify(res.data))
      }
      )

  }

  const toggleMenu = () => {
    setMenuOpen(menuOpen => !menuOpen)
  }

  return (
    <>
      <div className={styles.header}>
        Welcome to Hunvely world !!
      </div>

      <div className={styles.box}>
        <div style={{ display: 'flex' }}>
        
          <h1><Link href={""} style={{ flex: 1, justifyContent: 'center', textAlign: 'center' }} className="text-2xl">BOTTLER</Link></h1>
          <br />

          {/* <nav style={{ flex: 1, justifyContent: 'flex-end', textAlign: 'end' }} className={menuOpen ? 'active' : ''}>
            <PermIdentityIcon onClick={() => toggleMenu()} style={{ cursor: 'pointer' }}></PermIdentityIcon><br />
            <Link href={`${PG.USER}/login`}>sign in</Link><br />
            <Link href={`${PG.USER}/join`}>sign up</Link><br />
            <Link href={`${PG.USER}/withdrawal`}>user withdrawal</Link><br />

          </nav> */}
        </div>
        <div className={styles.content}>Start on your Journey.
        </div>
        <button className={styles.button}>
          <Link href={""}>Learn more</Link>
        </button>
      </div>
      <div style={{ justifyContent: 'space-between' }}>
        <div className={styles.body}>
          <div className={styles.gridContainer}>
            <h1 className={styles.leftAlignedText} style={{ color: "charcoal", fontSize: '30px', fontStyle: 'bold', marginTop: '100px' }}>위스키의 모든 것.</h1>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/st.jpeg)" }}>Whiskey History</Link>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/ct.webp)" }}>Category</Link>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/str.jpeg)" }}>Stores</Link>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/rc.jpg)" }}>Recommendations</Link>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/cm.jpeg)" }}>Community</Link>
            <Link href="" passHref className={styles.gridItem} style={{ backgroundImage: "url(/img/ev.jpeg)" }}>Events</Link>
          </div>
        </div>
      </div>

      <div style={{ display: 'flex', justifyContent: 'center', margin: '100px', position: 'relative', left: '15px' }}>
        <span style={{ marginRight: '15px', fontSize: '20px' }}>나의 위스키 찾기</span>
        <input type="text" onChange={handleChange} className={styles.input} />
        <SearchIcon onClick={handleClick} style={{ position: 'relative', top: '3px', right: '30px', cursor: 'pointer' }}></SearchIcon></div>

      <footer className={styles.footer}>
        <h5><Link href={""} className={styles.categories}>About BOTTLER</Link></h5>
        <h5><Link href={`${PG.BOARD}`} className={styles.categories}>Board</Link></h5>
        <h5><Link href={""} className={styles.categories}>Service</Link></h5>
        <br />
        Tel:+82-4816-9140 Email:jihoon9611@gmail.com <br />
        Cheongdam-dong, Gangnam-gu, Seoul, Republic of Korea <br /><br />
        Copyright © 2024. Hunvely. All Rights Reserved. <br /><br />
        <Link href={""} className="text-2xl text-center">BOTTLER</Link>
      </footer>
    </>
  )
}

export default HomePage

function setMenuOpen(arg0: (menuOpen: any) => boolean) {
  throw new Error("Function not implemented.");
}
