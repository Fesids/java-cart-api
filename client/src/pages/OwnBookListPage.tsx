import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router"
import { Link } from "react-router-dom";
import { BookComp } from "../components/BookComp";
import { AppContext } from "../context/AppContext";
import { Book } from "../interfaces/Book";

export const OwnBookListPage = () =>{
    const {user_id} = useParams();
    const [ownBookList, setOwnBookList] = useState([] as Array<Book>);
    const {GetOwnlistBook} = useContext(AppContext);
    const navigate = useNavigate()

    useEffect(() =>{
        let getUserId = 0
        if(user_id){
            getUserId = parseInt(user_id);
        }
        GetOwnlistBook(getUserId).then(data => setOwnBookList(data))
    });

    console.log(ownBookList)
    return(
        <div>
            <h1>View your own books</h1>
            
            {ownBookList.length? ownBookList.map(book => <BookComp {...book}/>): 
            <p>You've no book yet</p>}

            <button className="btn btn-success" onClick={()=> navigate("../", {replace:true})}>back</button>
        </div>
    )
}