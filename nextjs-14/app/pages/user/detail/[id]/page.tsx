'use client'

import { useRouter } from "next/navigation"
import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import {Box, Button, Input, TextField, Typography} from '@mui/material';
import { useSelector, useDispatch } from 'react-redux'
import { NextPage } from "next";
import { findAllBoards } from "@/app/components/board/service/board-service";
import { getAllBoards } from "@/app/components/board/service/board-slice";
import BoardColumns from "@/app/components/board/module/board-columns";
import { MyTypography } from "@/app/components/common/style/cell";
import { getAllUsers, getCountUsers, getUserById, jobHandler, passwordHandler, phoneHandler } from "@/app/components/user/service/user-slice";
import { deleteUser, findCountUsers, findUserById, modifyUser } from "@/app/components/user/service/user-service";
import { IUser } from "@/app/components/user/model/user";
import { PG } from "@/app/components/common/enums/PG";
// import React from "react";




export default function UserDetailPage (props:any) {

    const dispatch = useDispatch()
    const user : IUser = useSelector(getUserById)
    const router = useRouter();

    const changePasswordHandler = (e:any) => dispatch(passwordHandler(e.target.value))
    const changePhoneHandler = (e:any) => dispatch(phoneHandler(e.target.value))
    const changeJobHandler = (e:any) => dispatch(jobHandler(e.target.value))

    useEffect(() => {
        dispatch(findUserById(props.params.id))
    },[])

    const handleDelete = (e:any) => {
        dispatch(deleteUser(props.params.id))    
        alert("삭제되었습니다.")
        router.replace(`${PG.USER}/list`)
    }

    const handleModify = (e:any) => {
        dispatch(modifyUser(user))
        alert("변경되었습니다.")
        router.refresh()
    }



    return (
        <div>
        <h2>사용자 상세정보</h2>
        <p className="text-base">id : {user.id}</p>
            <p className="text-base">아이디 : {user.username}</p>
            <span className="text-base">비밀번호 : </span><Input className="text-base" placeholder={user.password} onChange={changePasswordHandler} /><br />
            <p className="text-base">이름 : {user.name}</p>
            <span className="text-base">전화번호 : </span><Input className="text-base" placeholder={user.phone} onChange={changePhoneHandler} /><br />
            <span className="text-base">직업 : </span><Input className="text-base" placeholder={user.job} onChange={changeJobHandler} /><br />
        <span>생성일자 : </span><Input id="regDate" defaultValue={user.regDate} readOnly/><br />
        <span>최근 변경 일자 : </span><Input id="modDate" defaultValue={user.modDate} readOnly/><br />
        <Button variant="outlined" onClick={handleModify}>수정</Button>
        <Button variant="outlined" color="error" onClick={handleDelete}>삭제</Button>
        </div>
    )
} 