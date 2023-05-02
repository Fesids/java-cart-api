import axios from "axios";
import { createContext, useEffect, useState } from "react";
import Cookie from "js-cookie"
import { Book, ICreateBook, IUpdateBook } from "../interfaces/Book";
import { User } from "../interfaces/User";
import { ICart } from "../interfaces/Cart";


interface AppContextProps{
    currentUser: User | null,
    cookieUser: string | null,
    books: Array<Book>,
    login(e:any, user:any):void,
    register(e:any, user:any):void,
    logout():void,

    //* book

    CreateBook(user_id:any, bookBody: any):void,
    GetBookById(id:number): Promise<Book>,
    DeleteBook(id:number): void,
    UpdateBook(id:number, updateBody:IUpdateBook): void,
    GetOwnlistBook(user_id:number): Promise<Array<Book>>,
    GetCartByUserId(user_id:number): Promise<ICart>,
    AddBookToCart(user_id:number, book_id:number):void,
    RemoveBookFromCart(user_id:number, book_id:number):void

}
export const AppContext = createContext({} as AppContextProps);


export const AppContextProvider = ({children}:React.PropsWithChildren) =>{
    const [currentUser, setCurrentUser] = useState<null | User>(JSON.parse(localStorage.getItem("currentuser") || "{}"));
    const [err, setErr] = useState("");
    const [cookieUser, setCookieUser] = useState("");

    //* book const
    const [books, setBooks] = useState([] as Array<Book>);

    



    //* AUTH functions

    const login = async (e:any, user:any) =>{
        e.preventDefault();
        try{
            const resp = await axios.post("api/auth/login", user);
            setCurrentUser(resp.data);
            setCookieUser(resp.data.token)
        } catch(err:any){
            setErr(err.response.data)
        }
    }

    const register = async (e:any, user:any) =>{
        e.preventDefault()
        try{
            const res = await axios.post("api/auth/register/COSTUMER", user);
            console.log(res);
        } catch(err:any){
            setErr(err.response.data)
        }

    }

    const logout = () =>{
        setCurrentUser(null)
        Cookie.remove("jwtkey");
    }

    /* 
        * useEffect to set currentUser
    */
    useEffect(()=>{
        localStorage.setItem("currentuser", JSON.stringify(currentUser));
        
    }, [currentUser]);

    //* BOOK functions

    useEffect(() =>{
        axios.get("api/book/all")
        .then(data => data.data)
        .then(setBooks);
    }, []);

    const CreateBook = async (user_id:any, bookBody: any) =>{
        try{
            await axios.post(`api/book/${user_id}/new`, bookBody);
        } catch(err){

        }
    }

    const GetBookById = async (id:number) =>{
        try{
            const resp = await axios.get(`api/book/${id}`)
            return resp.data;
        } catch(err){

        }
    }

    const DeleteBook = async (id:number) =>{
        try{
            await axios.delete(`api/book/${id}`);
        } catch(err){

        }
    }

    const UpdateBook = async (id:number | undefined, updateBody:IUpdateBook) =>{
        try{
            await axios.put(`api/book/${id}`, updateBody);
        } catch(err){

        }
    }
    
    const GetOwnlistBook = async (user_id:number) =>{
        try{
            const ownbooklist = await axios.get(`api/book/own_list_book/${user_id}`);
            return ownbooklist.data;
        } catch(err){

        }
    }

    const GetCartByUserId = async (user_id:number) =>{
        const cart = await axios.get(`api/cart/${user_id}`);
        return cart.data;
    }

    const AddBookToCart = async (user_id:number, book_id:number) =>{
        const add_book = await axios.post(`api/cart/add_book_to_cart/${user_id}/${book_id}`);
    }

    const RemoveBookFromCart = async (user_id:number, book_id:number) =>{
       await axios.delete(`api/cart/remove_book_cart/${user_id}/${book_id}`)
    }
    return(
        
        <AppContext.Provider value={{register, login, logout, currentUser, cookieUser, books, CreateBook, GetBookById, DeleteBook, UpdateBook, GetOwnlistBook, GetCartByUserId, AddBookToCart, RemoveBookFromCart}}>
            {children}
        </AppContext.Provider>

    )
}