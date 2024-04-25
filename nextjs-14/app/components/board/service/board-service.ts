import { createAsyncThunk } from "@reduxjs/toolkit";
import { IBoard } from "../model/board";
import { findAllBoardsAPI, findBoardByIdAPI } from "./board-api";
import axios from "axios";
import { API } from "@/app/components/common/enums/API";
import AxiosConfig from "@/app/components/common/configs/axios-config";

export const findAllBoards: any = createAsyncThunk(
    'boards/findAllBoards',
    async (page: number)=>{
        console.log('findAllBoards page : '+ page)
        const data:any = await  findAllBoardsAPI(1);
        const {message, result}:any = data
    
        return data
    }
)

export const findBoardById: any = createAsyncThunk(
    'boards/findBoardId',
    async (id: number) => {
        return await findBoardByIdAPI(id)
    }
)