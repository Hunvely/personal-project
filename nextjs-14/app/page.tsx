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
        <div>
          <Link href="/login" className={styles.container}>Sign in</Link>
        </div>
        <div>
          <Link href="/join" className={styles.container}>Sign up</Link>
        </div>
      </div>
      <h1 className={styles.title}>Whisky</h1>
      <p className={styles.subtitle}>Start on your journey.</p>
      <div className={styles.description}>
        <p>Whisky is a liquor with a assortment of scents and flavors.</p>
        <p>It is produced all over the world, and tastes shift depending on region, ingredients, and manufacturing process.</p>
      </div>
    </div>
  );
}
