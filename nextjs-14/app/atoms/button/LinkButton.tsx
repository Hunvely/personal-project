'use client'
import * as React from 'react';
import { PG } from '@/app/components/common/enums/PG'; 
import { useRouter } from 'next/navigation';
import { Box, Link } from '@mui/material';

interface ILinkButton{
    id: string,
    title: string,
    path: string
}

  export default function LinkButton ({id, title, path}:ILinkButton) {
    return(<>
        <Link
         href={`${path}`}
        className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700" aria-current="page">
            {title}
            </Link>
        </>)
  } 

  export const linkButtonTitles = [
    
    { id: '3', title: 'COUNTER', path: `${PG.DEMO}/redux-counter` },
    { id: '4', title: 'POSTS', path: `${PG.ARTICLE}/list` },
    { id: '5', title: 'BOARDS', path: `${PG.BOARD}/list` },
    { id: '6', title: 'USERS', path: `${PG.USER}/list` }
]

    export const settings = ['Profile','Account','Dashboard','Logout']