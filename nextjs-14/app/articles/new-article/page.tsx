"use client";
import { useState } from "react";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
const SERVER = "http://localhost:8080";

interface IArticle{
    company: string,
    contact: string,
    country: string
}

const Article = (props: IArticle) => {
  return (
    <tr>
      <td>{props.company}</td>
      <td>{props.contact}</td>
      <td>{props.country}</td>
    </tr>
  );
};

export default function Articles() {
  const article = [
    {
      Company: "Alfreds Futterkiste",
      Contact: "Maria Anders",
      Country: "Germany",
    },
    {
      Company: "Centro comercial Moctezuma",
      Contact: "Francisco Chang",
      Country: "Mexico",
    },
    { Company: "Ernst Handel", Contact: "Roland Mendel", Country: "Austria" },
    { Company: "Island Trading", Contact: "Helen Bennett", Country: "UK" },
    {
      Company: "Laughing Bacchus Winecellars",
      Contact: "Yoshi Tannamuri",
      Country: "Canada",
    },
    {
      Company: "Magazzini Alimentari Riuniti",
      Contact: "Giovanni Rovelli",
      Country: "Italy",
    },
  ];

  const articleList = article.map((v, i) => (
    <Article company={v.Company} contact={v.Contact} country={v.Country} />
  ));

  return (
    <>
      <h1>글 쓰기</h1>
      <table>
        <thead>
          <tr>
            <th>Company</th>
            <th>Contact</th>
            <th>Country</th>
          </tr>
        </thead>
        <tbody>{articleList}</tbody>
      </table>
    </>
  );
}
