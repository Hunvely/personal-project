import axios from "axios";
import { createAsyncThunk } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { IBoard } from "../model/board";
import { findAllBoards, findBoardById } from "./board-service";

interface BoardState {
  json: IBoard;
  array: Array<IBoard>;
}

export const initialState: BoardState = {
  json: {} as IBoard,
  array: [],
};

const boardThunks = [findAllBoards, findBoardById];
const status = {
  pending: "pending",
  fulfilled: "fulfilled",
  rejected: "rejected",
};
const handleFulfilled = (state: any, { payload }: any) => {
  console.log("------------------ conclusion ---------------");
  state.array = payload;
  console.log(state.array);
};
const handlePending = (state: any) => {};
const handleRejected = (state: any) => {};

export const boardSlice = createSlice({
  name: "board",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    const { pending, rejected } = status;
    builder
      .addCase(findAllBoards.fulfilled, handleFulfilled)
      .addCase(findBoardById.fulfilled, (state: any, { payload }: any) => {
        state.json = payload;
      });
  },
});

export const getAllBoards = (state: any) => {
  //getter
  console.log("------------------ Before useSelector ---------------");
  console.log(JSON.stringify(state.board.array));
  return state.board.array;
};
export const {} = boardSlice.actions;
export default boardSlice.reducer;
