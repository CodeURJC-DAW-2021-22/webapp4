import {Article} from "./article.model";

export interface Category {
    id_CATEGORY?: number;
    title: string;
    description: string;
    icon: string;
    categorys: Article[];
    size: number;
}
