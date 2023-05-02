import { useContext } from "react";
import { AppContext } from "../context/AppContext";
import { Book } from "../interfaces/Book";
import { BookActionComp } from "./BookComp";

export const SingleBookComp = (book:Book) =>{

    const {currentUser} = useContext(AppContext);
    
    return(
        <div className="container border mt-2 mb-3">
                <img src={book.image}></img>
                <h2><a href={`book/detail/${book.id}`} className="link">{book.title}</a></h2>
                <p>{book.description}</p>
                <p>{book.price}</p>
               
                
                
            </div>
    )
}