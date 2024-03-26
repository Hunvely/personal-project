"use client";
import { useState } from "react";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
const SERVER = "http://localhost:8080";

interface IArticle {
  id: number;
  title: string;
  content: string;
  writer: string;
  registerDate: string;
}

const Article = (props: IArticle) => {
  return ( // id는 보여지지 않아야 하기 때문에 td에 넣지 않고 tr에 key 값으로 넣어준다.
    <tr key = {props.id}>
      <td>{props.title}</td>
      <td>{props.content}</td>
      <td>{props.writer}</td>
      <td>{props.registerDate}</td>
    </tr>
  );
};

export default function Articles() {
  const articles = [
    {
      id: 0,
      title: "",
      content: "",
      writer: "",
      registerDate:""
    }
  ];

  const articlesList = articles.map((v) => (
    <Article {...v}/>
));

  return (
    <>
      <h1>글 목록</h1>
      <table>
        <thead>
          <tr>
            <th>title</th>
            <th>content</th>
            <th>writer</th>
            <th>registerDate</th>
          </tr>
        </thead>
        <tbody>{articlesList}</tbody>
      </table>
    </>
  );
}
