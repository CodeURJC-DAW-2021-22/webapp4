import { Article } from "./article.model";

export interface Report {
    id_REPORT?: number;
    article?: Article;
    email?: string;
    description?: string;
    //proof: boolean
}