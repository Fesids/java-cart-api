import { remove } from "lodash";
import { useContext, useEffect, useState } from "react";
import { unstable_renderSubtreeIntoContainer } from "react-dom";
import { useNavigate, useParams } from "react-router"
import { BookComp } from "../components/BookComp";
import { SingleBookComp } from "../components/SingleBookComp";
import { AppContext } from "../context/AppContext";
import { Book } from "../interfaces/Book";

export const SingleBookPage = () =>{
    const {GetBookById, currentUser, AddBookToCart, RemoveBookFromCart} = useContext(AppContext);
    const {id} = useParams();
    const [book, setBook] = useState({} as Book);
    const navigate = useNavigate()


    useEffect(()=>{
        let bookId = 0;
        if(id){

           bookId = parseInt(id);
        }
        GetBookById(bookId).then(data => setBook(data));
    }, [id]);

    let currentUserId = 0
    if(currentUser){
        currentUserId = currentUser.id;
    }
    const addToCart = ()=>{
        try{
            AddBookToCart(currentUserId, book.id);
            navigate("../cart/own_cart",{replace:true})
        } catch(error){

        }
    }

    const RemoveFromCart = (e:any) =>{
        e.preventDefault();
        let bookId = 0;
        let user_id = 0
        if(id){

           bookId = parseInt(id);
        }

        if(currentUser){
            user_id = currentUser.id
        }
        try{
            RemoveBookFromCart(user_id, bookId);
        } catch(error){

        }
    }


    console.log(book);
    return(
        <div className="single-book container">
            <SingleBookComp {...book}/>
            {/*{currentUser?.id == book.book_user.id? <p></p>:
            <button className="btn btn-success">add to Cart</button>}*/}
            <button className="btn btn-success" onClick={addToCart}>add to Cart</button>
            <button className="btn btn-danger" onClick={(e)=>RemoveFromCart(e)}>remove from cart</button>
        </div>
    )
}