export interface UserColumn{
    id? : number  // ?는 자바의 Optional 없어도 된다라는 뜻
    username? : string
    password? : string
    email? : string
    name? : string
    phone? : string
    job? : string
    regDate? : string
    modDate? : string
}