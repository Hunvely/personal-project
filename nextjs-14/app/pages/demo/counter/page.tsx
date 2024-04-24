'use client'
import { useState } from "react";
import { Button } from "@mui/material";
export default function Counter(){
    const [count , setCount ] = useState(0);
    const handlePlus = () =>{
        setCount(count+1)
    }
    const handleMinus = () =>{
        setCount(count-1)
    }
    return(
        <>
            <h1>React Counter : {count}</h1>
            <Button onClick={handlePlus}>+</Button>
            <Button onClick={handleMinus}>-</Button>
        </>
    );
}
