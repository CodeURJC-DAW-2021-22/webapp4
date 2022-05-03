import {User} from './user.model';

export interface Article {
    id_ARTICLE?: number;
    city: string;
    postal_Code: string;
    title: string;
    description: string;
    date: number;
    price: number;
    n_visit: number;
    reserved: boolean;
    userID: number;
    user: User;
    // categories: Category[];
    // photo: Blob;
    // categories: Category[];
    sold: boolean;
}
