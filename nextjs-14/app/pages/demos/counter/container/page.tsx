import { increase, decrease } from "@/lib/features/counter/counterReducer"
import { useCallback } from "react"
import { useSelector } from "react-redux"
import { useDispatch } from "react-redux"
import CounterComponent from "../component/page"

const CounterContainer = () => {
    const count = useSelector((state: any) => (state.counterReducer.count))
    const dispatch = useDispatch()
    const handlePlus = useCallback(() => dispatch(increase()), [dispatch])
    const handleMinus = useCallback(() => dispatch(decrease()), [dispatch])
    return (<>
        <CounterComponent count={count} handlePlus={handlePlus} handleMinus={handleMinus} />
    </>)
}
export default CounterContainer