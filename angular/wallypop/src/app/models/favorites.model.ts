import { Article } from "./article.model";
import { User } from "./user.model";


export interface Favorites {
    id_FAVORITE?: number;
    users: User;
    article: Article;
}