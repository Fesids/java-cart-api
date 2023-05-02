import { useContext } from "react"
import { Link } from "react-router-dom";
import { BookComp } from "../components/BookComp";
import { AppContext } from "../context/AppContext"

export const Home = () =>{
    const {books, currentUser} = useContext(AppContext);

    console.log(books)
    return(
        <div className="container">
            <h1 className="mt-2 mb-2">Home</h1>
            <div className="container">
                <h2 className="mt-2 mb-2">Latest books added</h2>

                {currentUser? <p><Link to={"/book/new"}className="link">+ add a new book</Link>  |  <Link to={"/book/list"} className="link">all book list</Link></p>
                : <p>Login or sign up to add a book</p>}
                
                {books.map(book => <BookComp {...book}/>).slice(-3).reverse()}
            </div>
        </div>
        
       
    )
}