export interface User{
    id: number,
    email: string,
    username: string,
    token: string
}

export interface UserLogin extends Omit<User, "id" | "token" | "usernamel">{
    password: string
}

export interface UserRegister extends Omit<User, "id" | "token"> {
   password: string
}