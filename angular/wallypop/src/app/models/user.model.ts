import {Article} from './article.model';

export interface User {
    id_USER?: number;
    full_NAME: string;
    password: string;
    name: string;
    tel: string;
    n_SOLD: number;
    n_SELL: number;
    is_ADMIN: boolean;
    articles: Article[];
}
