import { useContext, useReducer, useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './style.scss'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'


import axios from 'axios'
import { Layout } from './components/Layout'
import { Register } from './pages/Register'
import { Login } from './pages/Login'
import { Home } from './pages/Home'
import { CreateBookPage } from './pages/CreateBookPage'
import { SingleBookPage } from './pages/SingleBookPage'
import { UpdateBookPage } from './pages/UpdateBookPage'
import { OwnBookListPage } from './pages/OwnBookListPage'
import { OwnCart } from './pages/OwnCart'
import { Teste } from './pages/testeUpload'
import { BookListPage } from './pages/BookListPageBook'


axios.defaults.baseURL = "http://localhost:8081/"

const routes = createBrowserRouter([
  {
    path: "",
    element: <Layout/>,
    children:[
      {
        path: "",
        element: <Home/>
      },

      //* auth routes 
      {
        path: "/register",
        element: <Register/>
      },
      {
        path: "/login",
        element: <Login/>
      },

      //* book routes
      {
        path: "/book/new",
        element: <CreateBookPage/> 
      },
      {
        path: "/book/detail/:id",
        element: <SingleBookPage/>
      },
      {
        path: "/book/update/:id",
        element: <UpdateBookPage/>
      },

      {
        path: "book/own_book_list/:user_id",
        element: <OwnBookListPage/>
      },

      {
        path: "cart/own_cart",
        element: <OwnCart/>
      },

      {
        path: "/book/list",
        element: <BookListPage/>
      },
      {
        path: "teste",
        element: <Teste/>
      }
    ]

  }
])
/*const routes = createBrowserRouter([
  {
    path: "",
    element: <Layout/>,
    children:[
      {
        path:"",
        element:<Home/>
      },
      {
        path: "/add",
        element: <AddNewEmployee/>
      },
      {
        path: "/detail/:id",
        element: <DetailEmployee/>
      },{
        path: "/update/:id",
        element: <UpdateEmployee/>
      },{
        path: "/register",
        element: <Register/>
      }
    ]
  }
])*/

function App() {
  
  
  return(
   //<RouterProvider router={routes}/>
    <RouterProvider router={routes}/>
  )

  
}

export default App
