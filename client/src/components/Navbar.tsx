import { useContext, useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { AppContext } from "../context/AppContext"
import { ICart } from "../interfaces/Cart";

export const Navbar = () =>{

    const {currentUser, logout, GetCartByUserId} = useContext(AppContext);
    const navigate = useNavigate();
    /*const [cart, setCart] = useState({} as ICart);

    let userId = 0;
    if (currentUser?.id){
        userId = currentUser.id;
    }
    useEffect(()=>{
        GetCartByUserId(userId)
        .then(resp => setCart(resp));
    })*/
    const handleLogout = () =>{
        try{
            logout();
            navigate("", {replace:true})
        } catch(err){
            console.log(JSON.stringify(err))
        }
    }
    return(
        <div className="navbar-container">
           <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                <Link className="navbar-brand" typeof="" to={""}>Register system with Node</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                
                {currentUser? 
                <p>
                    <a className="btn btn-outline-dark" onClick={handleLogout}>logout</a>
                    <Link to={"cart/own_cart"} className='mr'>cart</Link>
                    <Link to={`book/own_book_list/${currentUser.id}`}>own_book_list</Link>
                </p>:
                <p>
                    <Link to={"login"} className="btn btn-outline-dark">login</Link>
                    <Link to={"register"} className="">Sign up</Link>
                </p>
                }
                
                
            </div>
        </nav>
        </div>
    )
}