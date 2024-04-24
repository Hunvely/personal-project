'use client'

import CategoryButton from "@/app/atoms/button/CategoryButton";
import MoveButton from "@/app/atoms/button/MoveButton";
import { IBoard } from "@/app/components/board/model/board";
import { findAllBoards } from "@/app/components/board/service/board-service";
import { getAllBoards } from "@/app/components/board/service/board-slice";
import { PG } from "@/app/components/common/enums/PG";
import { all } from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";


export default function BoardCategory() {

    const [boards, setBoards] = useState([])
    const dispatch = useDispatch()
    const allBoards: [IBoard] = useSelector(getAllBoards)

    useEffect(() => {
        dispatch(findAllBoards(1))
    }, [])


    return (<>

<h1>게시판목록</h1>
        {allBoards.map((board: IBoard) => (<CategoryButton id={board.id} title={board.title} description={board.description} />))}

    </>)
}