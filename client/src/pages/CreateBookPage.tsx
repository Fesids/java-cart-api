import { useContext, useState } from "react"
import { useNavigate } from "react-router";
import { AppContext } from "../context/AppContext";
import { ICreateBook } from "../interfaces/Book"

export const CreateBookPage = () =>{

    const [newBook, setNewBook] = useState({} as ICreateBook);
    const [img, setImg] = useState("");
    const {CreateBook, currentUser} = useContext(AppContext);
    const [redirect, setRedirect] = useState(false)
    const navigate = useNavigate();

    const handleChange=(e:any)=>(
        setNewBook({...newBook, [e.target.name]: e.target.value})
    );

    const handleCreateBook = (e:any) =>{

       
        e.preventDefault();
        
        const formData = new FormData();
        formData.append("image", img);
        formData.append("description", newBook.description);
        formData.append("title", newBook.title);
        formData.append("price", JSON.stringify(newBook.price));
        formData.append("author_book", newBook.author_book);
        
        try{
            CreateBook(currentUser?.id, formData);
            console.log(formData);
            setRedirect(true);
        } catch(err){

        }
    }

    if(redirect){
        navigate("../", {replace:true})
    }

    console.log(newBook);
    

    return(
        <div className="create-note-form" >
            <h2 className="mt-2 mb-2">Create a book</h2>
            
            <form method="post" onSubmit={(e) => handleCreateBook(e)} encType="multipart/form-data">

                <div className="form-group">
                    <label htmlFor="image" className="form-label mt-3">image : </label>
                    <input name="image" className="form-control" id="image" type={"file"} onChange={(e:any)=> setImg(e.target.files[0])}/>
                </div>

                <div className="form-group">
                    <label htmlFor="title" className="form-label mt-3">Title : </label>
                    <input name="title" className="form-control" id="title" onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="description" className="form-label mt-3">Description : </label>
                    <input name="description" className="form-control" id="description" onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="price" className="form-label mt-3">Price : </label>
                    <input name="price" className="form-control" id="price" type={"number"} onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="author_book" className="form-label mt-3">Author book : </label>
                    <input name="author_book" className="form-control" id="author_book" type={"text"} onChange={(e)=> handleChange(e)}/>
                </div>


                <input type={"submit"} value="post" className="btn btn-success mt-2 mb-2"/>
            </form>
        </div>
    )

}