'use client'
import UserColumns from "@/app/components/user/module/user-columns";
import { findAllUsers, findCountUsers } from "@/app/components/user/service/user-service";
import { getAllUsers, getCountUsers } from "@/app/components/user/service/user-slice";
import { DataGrid } from "@mui/x-data-grid";
import { NextPage } from "next";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";

const UserListPage : NextPage = () => {
    const [pageSize, setPageSize] = useState(5);
    
    const dispatch = useDispatch();
    const count = useSelector(getCountUsers)
    const allUsers : [] = useSelector(getAllUsers)

    useEffect(() => {
        console.log("1번 nnn  useEffect 내부")
        dispatch(findAllUsers(1))
        dispatch(findCountUsers())
    },[] )


    return(<>
    
    <h1 className="text-center">사용자 목록 || 총 회원 수 : {count}</h1>
        
        
        <div style={{ height: 400, width: "100%" }}>
        {allUsers && <DataGrid
            rows={allUsers}
            columns={UserColumns()}
            pageSizeOptions={[5, 10, 20]}
            checkboxSelection
        /> }
    </div>
    </>)
}

export default UserListPage