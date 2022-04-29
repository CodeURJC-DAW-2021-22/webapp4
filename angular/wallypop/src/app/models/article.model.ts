export interface Article {
    id_ARTICLE?: number;
    city: string;
    postal_code: string;
    title: string;
    description: string;
    date: number;
    price: number;
    n_visit: number;
    reserved: boolean;
    sold: boolean;

	// photo: Blob;
    // user: User;
    // categories: Category[];
}