import { InputRounded } from "@mui/icons-material";
import axios, { AxiosInstance } from "axios";
import { parseCookies } from "nookies";

// export default function AxiosConfig(){
//     return {
// Static 방식
//         headers: {
//             "Cache-Control": "no-cache",
//             "Content-Type": "application/json",
//             Authorization: `Bearer blah ~`,
//             "Access-Control-Allow-Origin": "*",
//         }
//     }
// }
// process.env.NEXT_PUBLIC_API_URL
export default function instance() {
  const instance = axios.create({ baseURL: process.env.NEXT_PUBLIC_API_URL});
  setInterceptor(instance);
  return instance;
}

export const setInterceptor = (inputInstance: AxiosInstance) => {
  inputInstance.interceptors.request.use(
    (config) => {
      // Dynamic 방식
      console.log("AXIOS intercepter에서 쿠키에서 토큰 추출");
      config.headers["Content-Type"] = "application/json";
      config.headers["Authorization"] = `Bearer ${parseCookies().accessToken}`;
      return config;
    },
    (error) => {
      console.log("AXIOS intercepter에서 발생한 에러 : " + error);
      return Promise.reject(error);
    }
  );

  inputInstance.interceptors.response.use((response) => {
    if (response.status === 404) {
      console.log(
        "AXIOS intercepter에서 발생한 에러로 토큰이 없을 때 404 페이지로 넘어감"
      );
    }
    return response;
  });
  return inputInstance;
};
