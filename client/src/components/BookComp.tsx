import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppContext";
import { Book } from "../interfaces/Book";

export const BookActionComp = ({id, title}:Book) =>{
    const {DeleteBook} = useContext(AppContext);
    const navigate = useNavigate();

    const handleDelete = (id:number) =>{
        try{
            //DeleteBook(id);
            if(confirm(`are you sure that you want to delete '${title}' book?`)){
                DeleteBook(id);
                alert(`book ${title} was deleted`)
                location.reload();
            } else{
                alert("book wasn't delete");
            }
        } catch(err){

        }
    }
    return(
        <div className="book_action mb-3">
            <button className="btn btn-danger del-btn" onClick={()=> handleDelete(id)}>delete</button>
            <button className="btn btn-primary" onClick={() => navigate(`../book/update/${id}`, {replace:true})}>update</button>
                
        </div>
    )
}

export const BookComp = (book:Book) =>{
        const {currentUser} = useContext(AppContext);
        const navigate = useNavigate();

        return(
            <div className="container border mt-2 mb-3">
                
                <p>
                    <img src={book.image} className='book-image mt-3'></img>
                </p>
                <h2><a className="link" onClick={()=> navigate(`../book/detail/${book.id}`, {replace:true})}>{book.title}</a></h2>
                <p>{book.description}</p>
                <p className="price-style">R$ {book.price}</p>
               
                
                {currentUser?.id == book.book_user.id? 
                
                <BookActionComp {...book} />:
                <p></p>}

                
            </div>
        )

        
}