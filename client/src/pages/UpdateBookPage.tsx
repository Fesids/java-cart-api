import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router"
import { AppContext } from "../context/AppContext";
import { ICreateBook } from "../interfaces/Book";


export const UpdateBookPage = () =>{

    const [upBook, setUpBook] = useState({} as ICreateBook);
    const {currentUser, UpdateBook, GetBookById} = useContext(AppContext);
    const [redirect, setRedirect] = useState(false)
    const navigate = useNavigate();
    const {id} = useParams();


    const handleChange=(e:any)=>(
        setUpBook({...upBook, [e.target.name]: e.target.value})
    );

    const handleUpdateBook = (e:any) =>{
       
        try{

            let bookId = 0;
            if(id){
    
               bookId = parseInt(id);
            }
            UpdateBook(bookId, upBook);
            setRedirect(true);
        } catch(err){

        }
    }

    useEffect(()=>{
        let bookId = 0;
        if(id){

            bookId = parseInt(id);
        }
        GetBookById(bookId).then(resp => setUpBook(resp));
    }, [id])


    if(redirect){
        let userId = 0;
        if (currentUser){
            userId = currentUser.id;
        }

        navigate(`../book/own_book_list/${userId}`, {replace:true})
    }

    console.log(upBook);

    return(
        <div className="create-note-form" >
            <h2 className="mt-2 mb-2">Update book</h2>
            
            <form method="post" onSubmit={(e) => handleUpdateBook(e)}>
                <div className="form-group">
                    <label htmlFor="title" className="form-label mt-3">Title : </label>
                    <input name="title" className="form-control" id="title" value={upBook.title} onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="description" className="form-label mt-3">Description : </label>
                    <input name="description" className="form-control" id="description" value={upBook.description} onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="price" className="form-label mt-3">Price : </label>
                    <input name="price" className="form-control" id="price" type={"number"} value={upBook.price} onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="author_book" className="form-label mt-3">Author_book : </label>
                    <input name="author_book" className="form-control" id="author_book" type={"text"} value={upBook.author_book} onChange={(e)=> handleChange(e)}/>
                </div>


                <input type={"submit"} value="post" className="btn btn-success mt-2 mb-2"/>
            </form>
        </div>
    )

}