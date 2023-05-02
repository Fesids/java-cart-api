import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { BookComp } from "../components/BookComp";
import { AppContext } from "../context/AppContext";
import { ICart } from "../interfaces/Cart";

export const OwnCart = () =>{

    const {currentUser, logout, GetCartByUserId, RemoveBookFromCart, AddBookToCart} = useContext(AppContext);
    const [cart, setCart] = useState({} as ICart);
    const navigate = useNavigate()

    let userId = 0;
    if (currentUser?.id){
        userId = currentUser.id;

    }

    const addToCart = (book_id:number)=>{
        try{
            AddBookToCart(userId, book_id);
            navigate("../cart/own_cart",{replace:true})
        } catch(error){

        }
    }

    
    const RemoveFromCart = (e:any, id:number) =>{
        e.preventDefault();
       
        let user_id = 0
        
        if(currentUser){
            user_id = currentUser.id
        }
        try{
            RemoveBookFromCart(user_id, id);
            location.reload();
        } catch(error){

        }
    }


    useEffect(()=>{
        GetCartByUserId(userId)
        .then(resp => setCart(resp));
    }, [currentUser]);

    console.log(cart.cart_books)

    return(
        <div>
            <h2>Cart</h2>
            {cart.cart_books?.length? 
            cart.cart_books.map(book =>
                <div className="mb-5">
                    <BookComp {...book}/>
                    <button className="btn btn-success" onClick={()=>addToCart(book.id)}>add to Cart</button>
                    <button className="btn btn-danger" onClick={(e)=>RemoveFromCart(e, book.id)}>remove from cart</button>
                </div>)
            :
            <p>add a book to cart and buy it</p>}
           
            <button onClick={()=> navigate("../book/list", {replace:true})} className="btn btn-success mt-6">back to list</button>
        </div>
    )
}