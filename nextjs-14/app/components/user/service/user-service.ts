import { createAsyncThunk } from "@reduxjs/toolkit";
import {
  findCountUsersAPI,
  findAllUsersAPI,
  findUserByIdAPI,
  deleteUserApi,
  modifyUserApi,
  loginAPI,
  existsUsernameApi,
} from "./user-api";
import { IUser } from "../model/user";

export const findAllUsers: any = createAsyncThunk(
  "users/findAllUsers",
  async (page: number) => {
    console.log("2번 findAllUsers page : " + page);
    const data: any = await findAllUsersAPI(1);
    return data;
  }
);

export const findUserById: any = createAsyncThunk(
  "users/findUserById",
  async (id: number) => await findUserByIdAPI(id)
);

export const findCountUsers: any = createAsyncThunk(
  "users/count",
  async () => await findCountUsersAPI()
);

export const deleteUser: any = createAsyncThunk(
  "users/delete",
  async (id: number) => {
    await deleteUserApi(id);
  }
);

export const modifyUser: any = createAsyncThunk(
  "users/modifyUser",
  async (user: IUser) => await modifyUserApi(user)
);

export const login: any = createAsyncThunk(
  "users/login",
  async (user: IUser) => await loginAPI(user)
);

export const existsByUsername: any = createAsyncThunk(
  "users/existsUsername",
  async (username: string) => await existsUsernameApi(username)
);
