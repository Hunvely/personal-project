'use client'
import Link from "next/link";
import React, { useState, useEffect } from "react"
import { useSelector, useDispatch } from 'react-redux'
import { useRouter } from "next/navigation"
import { login } from "@/app/components/user/service/user-service";
import { IUser } from "@/app/components/user/model/user";
import nookies, { parseCookies, destroyCookie, setCookie } from 'nookies'
import { getAuth } from "@/app/components/user/service/user-slice";
import { NextPage } from "next";
import { jwtDecode } from "jwt-decode";

const SERVER = 'http://localhost:8080'

const LoginPage: NextPage = () => {
    const router = useRouter()
    const dispatch = useDispatch()
    const auth = useSelector(getAuth)
    //const exists = useSelector(getExistsUsername)


    const [user, setUser] = useState({} as IUser) //하나의 instance로 만듬
    const [isWrongId, setIsWrongId] = useState(false)
    const [isTrueId, setIsTrueId] = useState(false)

    const [isWrongPw, setIsWrongPw] = useState(false)
    const [isTruePw, setIsTruePw] = useState(false)

    const [len, setLen] = useState('')

    //boolean type의 경우 is를 넣어줌
    const handleUsername = (e: any) => {
        const ID_CHECK = /^[a-zA-Z][a-zA-Z0-9]{5,19}$/
        //영어 대소문자로 시작하는 6~10자의 영어 대소문자 또는 숫자

        setLen(e.target.value)
        setUser({
            ...user,
            username: len
        })

        if (ID_CHECK.test(len)) {
            setIsWrongId(false)
            setIsTrueId(true)


        } else {
            setIsWrongId(true)
            setIsTrueId(false)

        }
    }


    const handlePassword = (e: any) => {
        const PN_CHECK = /^ $/
        // if(){}else{
        //   alert("비밀번호 형식이 올바르지 않습니다.")
        // }
        setUser({
            ...user,
            password: e.target.value
        })
    }

    const handleSubmit = () => {
        console.log('user ...' + JSON.stringify(user))
        //dispatch(existsUsername(user.username))
        dispatch(login(user))
    }

    useEffect(() => {
        console.log('getAuth 메시지' + auth.message)
        if (auth.message === 'SUCCESS') {
            setCookie({}, 'message', auth.message, { httpOnly: false, path: '/' })
            setCookie({}, 'token', auth.token, { httpOnly: false, path: '/' })
            console.log('서버에서 넘어온 메시지 ' + parseCookies().message)
            console.log('서버에서 넘어온 토큰 ' + parseCookies().token)
            console.log('토큰을 디코딩한 내용: ')
            jwtDecode<any>(parseCookies().token)
            router.push('/pages/board/list')
        } else {
            console.log('LOGIN FAIL')
        }
    }, [auth])

    return (
        <div className="flex items-center justify-center h-screen w-full px-5 sm:px-0">
            <div className="flex bg-white rounded-lg shadow-lg border overflow-hidden max-w-sm lg:max-w-4xl w-full">
                <div
                    className="hidden md:block lg:w-1/2 bg-cover bg-blue-700"
                    style={{
                        backgroundImage: `url("/images/chair.jpg")`,
                        backgroundSize: "cover"
                    }}
                ></div>
                <div className="w-full p-8 lg:w-1/2">
                    <p className="text-xl text-gray-600 text-center">Welcome back!</p>
                    <div className="mt-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">
                            Username alamblot0
                        </label>
                        <input
                            onChange={handleUsername}
                            className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
                            type="email"
                            required
                        />
                    </div>
                    {isWrongId && (<pre>
                        <h6 className=" text-red-500">
                            아이디의 형식이 올바르지 않습니다..
                        </h6>
                    </pre>)}
                    {isTrueId && (<pre>
                        <h6 className=" text-green-800">
                            올바른 아이디 형식입니다..
                        </h6>
                    </pre>)}
                    <div className="mt-4 flex flex-col justify-between">
                        <div className="flex justify-between">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                Password sV1@95
                            </label>
                        </div>
                        <input
                            onChange={handlePassword}
                            className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
                            type="password"
                        />
                        <a
                            href="#"
                            className="text-xs text-gray-500 hover:text-gray-900 text-end w-full mt-2"
                        >
                            Forget Password?
                        </a>
                    </div>
                    {isWrongPw && (<pre>
                        <h6 className="text-red 500">
                            비밀번호가 틀렸습니다.
                        </h6>
                    </pre>)}
                    <div className="mt-8">
                        <button className="bg-gray-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-gray-600"
                            onClick={handleSubmit}>
                            Login
                        </button>
                    </div>
                    <a
                        href="#"
                        className=" flex items-center justify-center mt-4 text-white rounded-lg shadow-md hover:bg-gray-100"
                    >
                        <div className="flex px-5 justify-center w-full py-3">
                            <div className="min-w-[30px]">
                                <svg className="h-6 w-6" viewBox="0 0 40 40">
                                    <path
                                        d="M36.3425 16.7358H35V16.6667H20V23.3333H29.4192C28.045 27.2142 24.3525 30 20 30C14.4775 30 10 25.5225 10 20C10 14.4775 14.4775 9.99999 20 9.99999C22.5492 9.99999 24.8683 10.9617 26.6342 12.5325L31.3483 7.81833C28.3717 5.04416 24.39 3.33333 20 3.33333C10.7958 3.33333 3.33335 10.7958 3.33335 20C3.33335 29.2042 10.7958 36.6667 20 36.6667C29.2042 36.6667 36.6667 29.2042 36.6667 20C36.6667 18.8825 36.5517 17.7917 36.3425 16.7358Z"
                                        fill="#FFC107"
                                    />
                                    <path
                                        d="M5.25497 12.2425L10.7308 16.2583C12.2125 12.59 15.8008 9.99999 20 9.99999C22.5491 9.99999 24.8683 10.9617 26.6341 12.5325L31.3483 7.81833C28.3716 5.04416 24.39 3.33333 20 3.33333C13.5983 3.33333 8.04663 6.94749 5.25497 12.2425Z"
                                        fill="#FF3D00"
                                    />
                                    <path
                                        d="M20 36.6667C24.305 36.6667 28.2167 35.0192 31.1742 32.34L26.0159 27.975C24.3425 29.2425 22.2625 30 20 30C15.665 30 11.9842 27.2359 10.5975 23.3784L5.16254 27.5659C7.92087 32.9634 13.5225 36.6667 20 36.6667Z"
                                        fill="#4CAF50"
                                    />
                                    <path
                                        d="M36.3425 16.7358H35V16.6667H20V23.3333H29.4192C28.7592 25.1975 27.56 26.805 26.0133 27.9758C26.0142 27.975 26.015 27.975 26.0158 27.9742L31.1742 32.3392C30.8092 32.6708 36.6667 28.3333 36.6667 20C36.6667 18.8825 36.5517 17.7917 36.3425 16.7358Z"
                                        fill="#1976D2"
                                    />
                                </svg>
                            </div>
                            <div className="flex w-full justify-center">
                                <h1 className="whitespace-nowrap text-gray-600 font-bold">
                                    Sign in with Google
                                </h1>
                            </div>
                        </div>
                    </a>
                    <div className="mt-4 flex items-center w-full text-center">
                        <Link
                            href="/pages/users/join"
                            className="text-xs text-gray-500 capitalize text-center w-full"
                        >
                            Don&apos;t have any account yet?
                            <span className="text-blue-700"> Sign Up</span>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;