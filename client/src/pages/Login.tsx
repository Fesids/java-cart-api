import { useContext, useState } from "react"
import { AppContext } from "../context/AppContext";
import { UserLogin } from "../interfaces/User"
import Cookie from "js-cookie"
import { useNavigate } from "react-router";

export const Login = () =>{
    const [loginU, setLoginU] = useState({} as UserLogin);
    const {login, currentUser, cookieUser} = useContext(AppContext);
    const [redirect, setRedirect] = useState(false);

    const navigate = useNavigate();


    const handleChange = (e:any) =>{
        setLoginU({...loginU, [e.target.name]: e.target.value})
    }

    const ul = {
        username: loginU.email,
        password: loginU.password
    }

    const loginUser = (e:any) =>{
        try{
            login(e, ul);
            Cookie.set("jwtkey", cookieUser?cookieUser:"", {expires:8})
            setRedirect(true)
        } catch(err){

        }
    }

    console.log(loginU);

    if(redirect){
        navigate("../", {replace:true})
    }
    return(
        
        <div className="create-note-form" >
            <h2 className="mt-2 mb-2">Login</h2>
            <p>Enter your credentials</p>
            <form method="post" onSubmit={(e)=>loginUser(e)}>

                <div className="form-group">
                    <label htmlFor="email" className="form-label mt-3">Email : </label>
                    <input name="email" className="form-control" id="email" onChange={(e)=>handleChange(e)}/>
                </div>

                <div className="form-group">
                    <label htmlFor="password" className="form-label mt-3">Password : </label>
                    <input type={"password"} name="password" className="form-control" id="password" onChange={(e)=>handleChange(e)}/>
                </div>


                <input type={"submit"} value="login" className="btn btn-success mt-3 mb-2"/>
            </form>
        </div>

    )
}