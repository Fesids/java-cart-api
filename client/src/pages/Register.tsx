import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router";
import { AppContext } from "../context/AppContext"
import { UserRegister } from "../interfaces/User"


export const Register = () =>{

    const [user, setUser] = useState({} as UserRegister);
    const [redirect, setRedirect] = useState(false);
    const {register} = useContext(AppContext);
    const navigate = useNavigate();


    const handleChange = (e:any) =>{
        setUser({...user, [e.target.name]: e.target.value})
    }

    const registerUser = (e:any) =>{
        try{
            register(e, user);
            setRedirect(true);
        } catch(err){

        }
    }


    console.log(user);

    if(redirect){
        navigate("../login", {replace:true})
    }
    
    return(
        
        <div className="create-note-form" >
            <h2 className="mt-2 mb-2">Register</h2>
            <p>Enter your credentials</p>
            <form method="post" onSubmit={(e)=> registerUser(e)}>
                <div className="form-group">
                    <label htmlFor="username" className="form-label mt-3">Username : </label>
                    <input name="username" className="form-control" id="username" onChange={(e)=> handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="email" className="form-label mt-3">Email : </label>
                    <input name="email" className="form-control" id="email" onChange={(e)=>handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="password" className="form-label mt-3">Password : </label>
                    <input type={"password"} name="password" className="form-control" id="password" onChange={(e)=>handleChange(e)}/>
                </div>


                <input type={"submit"} value="create" className="btn btn-success mt-3 mb-2"/>
            </form>
        </div>
    )

}