import  instance from "@/app/components/common/configs/axios-config";
import { IUser } from "../model/user";

export const findAllUsersAPI = async (page: number) => {
  try {
    console.log("3번");
    const response = await instance().get("/users/list", {
      params: { page, size: 10, limit: 10 },
    });
    return response.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};

export const findUserByIdAPI = async (id: number) => {
  try {
    return (
      await instance().get("/users/detail", {
        params: { id },
      })
    ).data;
  } catch (error) {
    return error;
  }
};

export const findCountUsersAPI = async () => {
  try {
    return (await instance().get(`/users/count`)).data;
  } catch (error) {
    return error;
  }
};

export const deleteUserApi = async (id: number) => {
  try {
    await instance().delete(`/users/delete`, {
      params: { id },
    });
  } catch (error) {
    return error;
  }
};

export const modifyUserApi = async (user: IUser) => {
  try {
    return (await instance().put(`/users/modify`, user)).data;
  } catch (error) {
    return error;
  }
};

export const loginAPI = async (user: IUser) => {
  console.log(` 로그인 API에 넘어온 파라미터 : ${JSON.stringify(user)}`);
  try {
    const response = await instance().post(`/auth/login`, user);
    // Java 에서 Messenger.message 에 값을 담음
    return response.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};

export const existsUsernameAPI = async (username: string) => {
  try {
    const response = await instance().get(`/auth/exists-username`, {
      params: { username },
    });
    console.log("existsUsernameAPI 결과: " + response.data);
    return response.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};
export const logoutAPI = async () => {
  try {
    const response = await instance().get(`/users/logout`)
    console.log("logoutAPI 결과: " + response.data);
    return response.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};
