'use client';
import axios from 'axios';
import { useState } from 'react';
import { useRouter } from "next/navigation";
import { API } from '@/app/components/common/enums/API';
import AxiosConfig from '@/app/components/common/configs/axios-config';
import { NextPage } from 'next';

const JoinPage: NextPage = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [phone, setPhone] = useState('')
    const [email, setEmail] = useState('')
    const [job, setJob] = useState('')
    const [height, setHeight] = useState('')
    const [weight, setWeight] = useState('')
    const handleUsername = (e: any) => {
        setUsername(e.target.value)
    }
    const handlePassword = (e: any) => {
        setPassword(e.target.value)
    }
    const handleName = (e: any) => {
        setName(e.target.value)
    }
    const handlePhone = (e: any) => {
        setPhone(e.target.value)
    }
    const handleEmail = (e:any) => {
        setEmail(e.target.value)
    }
    const handleJob = (e: any) => {
        setJob(e.target.value)
     }
    const handleHeight = (e: any) => {
        setHeight(e.target.value)
    }
    const handleWeight = (e: any) => {
        setWeight(e.target.value)
    }
    const router = useRouter();

    const handleSubmit = (e: any) => {
        e.preventDefault()
        axios.post(`${API.SERVER}/users/save`, 
                    { username, password, name, phone, email, job }, 
                    AxiosConfig())
                        .then(res => { 
                            alert(JSON.stringify(res.data)) 
                            router.push("/pages/user/login")
                        })
                        .catch(error => {
                            console.error('회원가입 에러:', error);
                            alert('회원가입에 실패하였습니다.');
                        });
    }   
    return (<>
        <div className="container">
            <h1>Sign Up</h1>
            <p>Please fill in this form to create an account.</p>
            <hr />
            <label htmlFor="username"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required onChange={handleUsername} /><br />
            <label htmlFor="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required onChange={handlePassword} /><br />
            <label htmlFor="name"><b>Name</b></label>
            <input type="name" placeholder="Enter Name" name="name" required onChange={handleName} /><br />
            <label htmlFor="phone"><b>Phone</b></label>
            <input type="phone" placeholder="Enter Phone" name="phone" required onChange={handlePhone} /><br />     
            <label htmlFor="email"><b>E-Mail</b></label>
            <input type="email" placeholder="Enter email" name="email" required onChange={handleEmail} /><br />
            <label htmlFor="job"><b>Job</b></label>
            <input type="job" placeholder="Enter Job" name="job" required onChange={handleJob} /><br />
   
            {/* <label>
                <input type="checkbox" checked={true} name="remember" style={{ marginBottom: "15px" }} /> Remember me
            </label> */}    
            <p>By creating an account you agree to our <a href="#" style={{ color: "dodgerblue" }}>Terms & Privacy</a>.</p>
            <div className="clearfix">
                <button type="button" className="cancelbtn">Cancel</button><br /><br />
                <button type="submit" className="signupbtn" onClick={handleSubmit}>Sign Up</button>
            </div>
        </div>
    </>)
}

export default JoinPage;