import { Book } from "./Book";

export interface ICart{
    cart_id: number,
    user: any,
    cart_books: Array<Book>
}