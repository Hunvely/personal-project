import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { createSlice } from "@reduxjs/toolkit";
import { IArticle } from '../model/article';
import { initialState } from './article-init';
import { findAllArticles, findArticleById, findCountArticles } from './article-service';

const articleThunks = [findAllArticles]

const status = {
    pending: 'pending',
    fulfilled: 'fulfilled',
    rejected: 'rejected'
}

const handleFulfilled =  (state: any, {payload}: any) => {
    console.log('------------------ conclusion ---------------')
    state.array = payload
    console.log(state.array)

}


const handlePending = (state: any) => {
  
}

const handleRejected = (state: any) => {
  
}

export const articleSlice = createSlice({
    name: "article",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder
        .addCase(findAllArticles.fulfilled, (state: any, {payload}: any) => {state.array = payload}) //article을 생략한것 왜냐하면 article에 파일에 있으니까
        //switch case랑 유사 (findAllArticles.fulfilled면 handleFulfilled함수를 실행해라)
        .addCase(findArticleById.fulfilled, (state: any, {payload}: any) => {state.array = payload})
        .addCase(findCountArticles.fulfilled, (state : any, {payload}: any) => {state.count = payload})
    }
})
export const getAllArticles = (state: any) => state.article.array // getter
export const getArticleById = (state: any) => state.article.array
export const getCountArticles = (state: any) => state.article.count

export const {} = articleSlice.actions

export default articleSlice.reducer;