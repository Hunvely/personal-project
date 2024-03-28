'use client';
import { Button } from "@mui/material";
import { useState } from "react";
export default function Counter() {
    const [count, setCount] = useState(0);
    const handleClickPlus = () => {
        setCount(count+1);
    }
    const handleClickMinus = () => {
        setCount(count-1);
    }
    return (<>
    <h1>Counter : {count}</h1>
    <Button variant="outlined" color="success" onClick={handleClickPlus}>+</Button>
    <Button variant="outlined" color="error" onClick={handleClickMinus}>-</Button>
    </>)
}