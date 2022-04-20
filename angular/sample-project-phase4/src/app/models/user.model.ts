export interface User {
    id?: number;
    full_name: string;
    password: string;
    email: string;
    tel: string;
    n_sold: number;
    n_sell: number;
    is_admin: boolean;
    //articles: Article[];
    //favorites: Favorites[];
}