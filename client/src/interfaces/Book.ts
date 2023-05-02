export interface Book{
    id: number,
    image: string,
    title: string,
    description: string,
    price: number,
    author_book: string,
    book_user: any

}

export interface ICreateBook extends Omit<Book, "id" | "book_user">{

}

export interface IUpdateBook extends Omit<Book, "id" | "book_user">{

}