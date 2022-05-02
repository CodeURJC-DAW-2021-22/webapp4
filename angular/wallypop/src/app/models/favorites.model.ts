import { Article } from "./article.model";
import { User } from "./user.model";


export interface Favorites {
    id_CATEGORY?: number;
    users: User[];
    articles: Article[];
}