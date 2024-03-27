"use client";
import axios from "axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
const SERVER = "http://localhost:8080";

interface IArticle {
  id: number;
  title: string;
  content: string;
  writer: string;
  registerDate: string;
}

export default function Articles() {
  const router = useRouter;
  const [articles, setArticles] = useState([]);

  const url = `${SERVER}/api/articles`;
  const config = {
    headers: {
      "Cache-Control": "no-cache",
      "Content-Type": "application/json",
      Authorization: `Bearer blah ~`,
      "Access-Control-Allow-Origin": "*",
    },
  };

  useEffect(() => {
    axios.get(url, config).then((res) => {
      const message = res.data.message;
      console.log(message);
      if (message === "SUCCESS") {
        const arr = res.data.result;
        for (let i of arr) {
          console.log(i);
        }
        setArticles(res.data.result);
        for (const element of articles) {
          console.log(element);
        }
      } else if (message === "FAIL") {
        alert("게시물이 없습니다.");
      } else {
        alert("지정되지 않은 값");
      }
    });
  }, []);

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
        <tbody>
          {articles.map((props: IArticle) => (
            // id는 보여지지 않아야 하기 때문에 td에 넣지 않고 tr에 key 값으로 넣어준다.
            <tr key={props.id}>
              <td>{props.title}</td>
              <td>{props.content}</td>
              <td>{props.writer}</td>
              <td>{props.registerDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}
