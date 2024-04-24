'use client'
import MoveButton from "@/app/atoms/button/MoveButton";
import articleColumns from "@/app/components/article/module/article-columns";
import { findAllArticles, findArticleById, findCountArticles } from "@/app/components/article/service/article-service";
import { getAllArticles, getArticleById, getCountArticles } from "@/app/components/article/service/article-slice";
import { PG } from "@/app/components/common/enums/PG";
import { alpha, gridClasses, styled } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { NextPage } from "next";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";

const cards = [
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/mountain-nightview.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/autumn.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/babypinetree.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/beach.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/purpleflowers.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/starrysky.jpg",
    "https://www.tailwindtap.com/assets/components/horizontal-carousel/lake.jpg",
  ];

const articleListPage : NextPage = () => {
    const [pageSize, setPageSize] = useState(5);
    
    const dispatch = useDispatch();
    const count = useSelector(getCountArticles)
    const allArticles: [] = useSelector(getAllArticles)

    useEffect(() => {
        console.log("1번 nnn  useEffect 내부")
        dispatch(findAllArticles(1))
        dispatch(findCountArticles())
    },[] )


    return(<>
    <MoveButton text={"글쓰기"} path={`${PG.ARTICLE}/save`}></MoveButton><br />
     <div className="flex flex-col items-center justify-center w-full">
      <div className="flex overflow-x-scroll snap-x snap-mandatory max-w-6xl no-scrollbar">
        {cards.map((data, index) => {
          return (
            <section
              className="flex-shrink-0 w-full snap-center justify-center items-center"
              key={index}
            >
              <img
                src={data}
                alt="Images to scroll horizontal"
                className="w-full h-[500px]"
              />
            </section>
          );
        })}
      </div>
      </div>
    
    
        <div style={{ height: 890, width: "100%" }}>
        {allArticles && <DataGrid
                    rows={allArticles}
                    columns={articleColumns()}
                    initialState={{
                      pagination: {
                        paginationModel: {
                          pageSize: 15,
                        },
                      },
                    }}
                    pageSizeOptions={[5, 10, 20]}
                    checkboxSelection
                /> }
    </div>
    </>)
}

export default articleListPage