import axios from "axios";
import { useState } from "react"

interface Image{
    file: string
}
export const Teste = () =>{

    const [image, setImage] = useState({} as Image);
    const [img, setImg] = useState("");
    const [testeImg, setTesteImg] = useState("");

    const handleChange = (e:any) =>{
        setImage({...image, [e.target.name]: e.target.value});
        
    }

    const handleTesteGetImage = () =>{
        /*axios.get("api/book/teste/Captura de tela_20230423_191233.png")
        .then(resp => setTesteImg(resp.data));*/
        setTesteImg('http://127.0.0.1:8887/Captura%20de%20tela_20230311_174425.png')
    }

    const handleOnSubmit = async (e:any)=>{
        e.preventDefault()
        var formData = new FormData()
        formData.append("file", img);
        
        /*await axios.post("api/book/teste",formData,{
            headers:{"Content-type": "application/x-www-form-urlencoded",
        "Accept":"application/json"}
        });*/
        await axios.post("api/book/teste",formData);
    }

    console.log(image)

    console.log("testeget "+testeImg)

    return(
        <div>
           <form method="post" encType="multipart/form-data" onSubmit={(e)=>handleOnSubmit(e)}>
                <input type="file" name='file' id="file" key="file" onChange={(e:any)=>setImg(e.target.files[0])}></input>
                <input type={"submit"} ></input>
           </form>

           <img src={testeImg}>

           </img>
           <button onClick={handleTesteGetImage}>peguuiii</button>
        </div>
    )
}