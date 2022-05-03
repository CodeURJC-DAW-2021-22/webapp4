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
    sold: boolean;

	// photo: Blob;
    userID: bigint;
    user: User;
    // categories: Category[];
}
