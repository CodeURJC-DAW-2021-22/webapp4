import {User} from './user.model';

export interface Article {
    id_ARTICLE?: number;
    city: string;
    postal_CODE: string;
    title: string;
    description: string;
    date: number;
    price: number;
    n_VISITS: number;
    reserved: boolean;
    user: User;
    sold: boolean;
}
