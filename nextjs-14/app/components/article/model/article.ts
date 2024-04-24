export interface IArticle{
    id? : number
    title? : string
    content? : string
    regDate? : string
    modDate? : string
    writerId? : number
    boardId? : number
    count? : number
    json? : IArticle
    array? : IArticle[]
}