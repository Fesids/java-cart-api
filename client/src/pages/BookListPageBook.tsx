import { useContext } from "react"
import { BookComp } from "../components/BookComp"
import { AppContext } from "../context/AppContext"

export const BookListPage = () =>{

    const {books} = useContext(AppContext)

    return(
        <div>
            <h1 className="mt-3">All Books</h1>
            {books.length? books.map(book =>
                <BookComp {...book}/>):<p>No books registered</p>}
        </div>
    )
}