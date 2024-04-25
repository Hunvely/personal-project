'use client'

import axios from "axios"
import { useRouter } from "next/navigation"
import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import { Box, Button, Input } from '@mui/material';
import AxiosConfig from "@/app/components/common/configs/axios-config";
import { API } from "@/app/components/common/enums/API";
import { NextPage } from "next";
import { MyTypography } from "@/app/components/common/style/cell";
import { AttachFile, FmdGood, ThumbUpAlt } from "@mui/icons-material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { jwtDecode } from "jwt-decode";
import { parseCookies } from "nookies";
import { findAllBoards, findBoardById } from "@/app/components/board/service/board-service";
import { saveArticle } from "@/app/components/article/service/article-service";
import { useSelector } from "react-redux";
import { IBoard } from "@/app/components/board/model/board";
import { getAllBoards } from "@/app/components/board/service/board-slice";
// import React from "react";


const RegisterPage: NextPage = () => {

  const { register, handleSubmit, formState: { errors } } = useForm();
  


  const [content, setContent] = useState("")

  const selectHandler = (e: any) => {
    setContent(e.target.value)
  }

  const dispatch = useDispatch() // 무조건 씀
  const boards : [] = useSelector(getAllBoards)
  const router = useRouter()
  
  const handelCancel = (e: any) => { }

  const options = [
    { id: 1, title: 'reviews', content: 'Review' },
    { id: 2, title: 'qna', content: 'Q&A' },
    { id: 3, title: 'free', content: '자유게시판' }
  ]

  const onSubmit = (data: any) => {
    data.boardId = parseInt(data.boardId);

    alert(JSON.stringify(data))
    
    dispatch(saveArticle(data))
      .then((res: any) => {

        const boardId = res.payload.id
        alert(`게시물 작성 완료 ${boardId}`)
        router.push(`/pages/article/list/${boardId}`)

      })
      .catch((err: any) => {

      });
  }



  return (<>


    <form onSubmit={handleSubmit(onSubmit)} className="max-w-sm mx-auto">
      <label htmlFor="countries" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Select your board</label>
      <select defaultValue={1}
        {...register('boardId', { required: true })}
        id="countries" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
        {boards.map((item : IBoard) => (
          <option key={item.id} title={item.title} value={item.id}>{item.title}</option>
        ))
        }
      </select>


      <div className="editor mx-auto w-10/12 flex flex-col text-gray-800 border border-gray-300 p-4 shadow-lg max-w-2xl">
        {MyTypography('Article 작성', "1.5rem")}
        <input type="hidden" {...register('writerId', { required: true })} value={jwtDecode<any>(parseCookies().accessToken).id} readOnly />
        <input
          {...register('title', { required: true, maxLength: 50 })}
          className="title bg-gray-100 border border-gray-300 p-2 mb-4 outline-none" placeholder="Title" type="text" name="title" />
        <textarea
          {...register('content', { required: true, maxLength: 333 })}
          className="description bg-gray-100 sec p-3 h-60 border border-gray-300 outline-none" placeholder="Describe everything about this post here" name="content" ></textarea>
        {/* <!-- icons --> */}
        <div className="icons flex text-gray-500 m-2">
          <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <ThumbUpAlt component={ThumbUpAlt}></ThumbUpAlt>
          </svg>
          <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <FmdGood component={FmdGood}></FmdGood>
          </svg>
          <svg className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <AttachFile component={AttachFile}></AttachFile>
          </svg>
          <div className="count ml-auto text-gray-400 text-xs font-semibold">0/300</div>
        </div>
        {/* <!-- buttons --> */}
        <div className="buttons flex">
          <div className="btn  overflow-hidden relative w-30 bg-white text-blue-500 p-3 px-4 rounded-xl font-bold uppercase -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
        before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-00"
            onClick={handelCancel}>Cancel</div>
          {/* <div className="btn  overflow-hidden relative w-30 bg-blue-500 text-white p-3 px-8 rounded-xl font-bold uppercase -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
        before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-00"
            onSubmit ={handleSubmit}> Post </div> */}
          <input type="submit" value="SUBMIT" />
        </div>
      </div>
    </form>

  </>)
}

export default RegisterPage
