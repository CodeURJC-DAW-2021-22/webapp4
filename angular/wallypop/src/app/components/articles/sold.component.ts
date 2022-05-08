import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {ActivatedRoute, Router} from '@angular/router';
import { FavoriteService } from 'src/app/services/favorites.service';

@Component({
    selector: 'sold',
    templateUrl: './sold.component.html'
})
export class SoldComponent implements OnInit {
    articles: Article[];
    categories: Category[];
    category: Category;
    idCategory: number;
    filtered: boolean;
    query: string;
    city: string;
    // tslint:disable-next-line:max-line-length
    constructor(private articleService: ArticleService, private categoryService: CategoryService, private favoriteService: FavoriteService, public loginService: LoginService, private routing: ActivatedRoute, private router: Router) {
        this.idCategory = -1;
        this.filtered = false;
    }

    ngOnInit(): void {
        this.getCategories();
        this.getAllArticles();
    }

    getCategories(): void {
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
        );
    }

    getAllArticles(): void {
        this.articleService.getArticles().subscribe(
            article => this.articles = article,
            error => console.log(error)
        );
    }

    addFavorite(idUser: number|string, idArticle: number|string): void {
        this.favoriteService.addFavorite(idUser, idArticle);
    }
}
