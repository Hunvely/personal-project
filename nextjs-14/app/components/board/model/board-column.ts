export interface BoardColumn{
    id? : number  // ?는 자바의 Optional 없어도 된다라는 뜻
    boardName? : string
    boardType? : string
    regDate?: string
    modDate?: string
}