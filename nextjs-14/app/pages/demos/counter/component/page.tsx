import { Button } from "@mui/material"
import React from "react"

interface Props {
    count: number,
    handlePlus: () => 0,
    handleMinus: () => 0
}

const CounterComponent = React.memo(({ count, handlePlus, handleMinus }: Props) => {
    return (<>
        <h1>Counter : {count}</h1>
        <Button onClick={handlePlus}>+</Button>
        <Button onClick={handleMinus}>-</Button>
    </>)
})

export default CounterComponent