import { Link, Typography } from "@mui/material";
import { GridColDef } from "@mui/x-data-grid";
import { ArticleColumn } from "../model/article-column";
import { PG } from "../../common/enums/PG";

interface CellType {
    row: ArticleColumn
}

export default function ArticleColumns(): GridColDef[] {


    return [
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'id',
            headerName: 'No.',
            renderCell: ({ row }: CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>  {row.id}</Typography>
        },
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'title',
            headerName: '제목',
            renderCell: ({ row }: CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>  <Link href={`${PG.ARTICLE}/detail/${row.id}`} >
                {row.title}</Link></Typography>
        },
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'content',
            headerName: '내용',
            renderCell: ({ row }: CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>{row.content}</Typography>
        },
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'registerDate',
            headerName: '등록일',
            renderCell: ({ row }: CellType) => <Typography textAlign="center" sx={{ fontSize: "1.2rem" }}>  {row.regDate}</Typography>
        }
    ]

}