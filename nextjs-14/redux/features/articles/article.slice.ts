import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from 'axios';
import {createSlice} from "@reduxjs/toolkit"
import { initialState } from "./article.init";
import { getAllArticles } from "./article.service";
import { getAllArticleAPI } from "./article.api";

const articleThunks = [getAllArticles]

const status = {
    pending:'pending',
    fulfilled:'fulfilled',
    rejected:'rejected'
}

const handlePending = (state: any) => {
    
}
const handleFulfilled = (state: any) => {
    
}
const handleRejected = (state: any) => {
    
}
export const articleSlice = createSlice({
    name: "article",
    initialState,
    reducers:{},
    extraReducers: builder => {
        const {pending,rejected} = status;

        builder
        .addCase(getAllArticles.fulfilled, (state,{payload}:any) => {
            console.log('-------colclusion-------')
            console.log(JSON.stringify(payload))
        })
    }

})

export const {} = articleSlice.actions

export default articleSlice.reducer;