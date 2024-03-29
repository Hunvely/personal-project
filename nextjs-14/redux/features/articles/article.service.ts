import { createAsyncThunk } from "@reduxjs/toolkit";
import { IArticle } from "./article.model";
import { getAllArticleAPI } from "./article.api";
import axios from "axios";
import { API } from "@/redux/common/enums/API";
import AxiosConfig from "@/redux/common/configs/axios-config";

export const getAllArticles: any = createAsyncThunk(
  "articles/getAllArticles",
  async(page: number, { rejectWithValue }) => {
    console.log("getArticles page : " + page)
    const {message,result}:any = await getAllArticleAPI(1);
    console.log('------API를 사용한 경우------')
    console.log('message : ' + message)
    console.log(JSON.stringify(result))    
  }
);
