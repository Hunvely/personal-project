'use client';
import { useState } from "react";
import axios from 'axios';
import Link from "next/link";
import styles from './main.module.css';
const SERVER = 'http://localhost:8080'

export default function Home() {

  const [name, setName] = useState('')
 
  return (
  
    <div className={styles.container}>
       <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <div style={{ marginRight: '10px' }}>
          <Link href="/login">로그인</Link>
        </div>
        <div>
          <Link href="/join">회원가입</Link>
        </div>
      </div>
      <h1 className={styles.title}>Whiskey</h1>
      <p className={styles.subtitle}>당신의 위스키 여정을 시작하세요.</p>
      <div className={styles.description}>
        <p>위스키는 다양한 향과 맛을 즐길 수 있는 음료입니다.</p>
        <p>위스키는 세계 각지에서 생산되고 있으며, 그 맛의 차이는 지역, 재료, 제조 과정에 따라 다릅니다.</p>
      </div>
    </div>
  );
}
