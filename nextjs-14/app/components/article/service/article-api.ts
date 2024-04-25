import  instance  from '@/app/components/common/configs/axios-config'
import { IArticle } from '../model/article'

export const findAllArticlesAPI = async (page: number) =>{
    try{
        const response = await instance().get('/articles/list',{
            params: {page, limit: 10}
        })
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}

export const findArticleByIdAPI = async (id : number) => {
    try {
        const response = await instance().get(`/articles/list`,{
            params : {id}
        })
        return response.data
    } catch (error) {
        return error
    }
}

export const findCountArticlesAPI = async () => {
    try {
        return (await instance().get(`/articles/count`)).data
    } catch (error) {
        return error
    }
}

export const deleteArticleAPI = async (id : number) => {
    try {
        await instance().delete(`/articles/delete`, {
            params : {id}
        })
    } catch (error) {
        return error
    }
}

export const saveArticleAPI = async (articleDto :IArticle) => {
    try {
        return (await instance().post(`/articles/save`, articleDto)).data
    } catch (error) {
        return error
    }
}