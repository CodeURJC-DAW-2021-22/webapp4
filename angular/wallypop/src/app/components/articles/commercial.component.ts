import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'commercial',
    templateUrl: './commercial.component.html'
})
export class CommercialComponent implements OnInit {
    articles: Article[];
    categories: Category[];
    category: Category;
    idCategory: number;
    filtered: boolean;
    query: string;
    city: string;
    // tslint:disable-next-line:max-line-length
    constructor(private articleService: ArticleService, private categoryService: CategoryService, public loginService: LoginService, private routing: ActivatedRoute, private router: Router) {
        this.idCategory = -1;
        this.filtered = false;
    }

    ngOnInit(): void {
        this.getCategories();
        this.idCategory = this.routing.snapshot.params.id;
        if (this.idCategory !== undefined) {
            this.getArticlesFromCategory(this.idCategory);
        } else {
            this.queryParams();
            if (this.filtered) {
                this.getFilteredArticles(this.query, this.city);
            } else {
                this.getAllArticles();
            }
        }
    }

    queryParams(): void {
        this.routing.queryParams.subscribe(params => {
            if (params.query !== undefined && params.city !== undefined) {
                this.filtered = true;
                this.query = params.query;
                this.city = params.city;
            } else {
                this.filtered = false;
            }
        });
    }

    getCategories(): void {
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
        );
    }

    getArticlesFromCategory(id: number | string): void {
        this.categoryService.getCategory(id).subscribe(
            articles => this.articles = articles,
            error => {
                console.log(error);
                alert('No existe esta categoría o no hay artículos disponibles');
                this.router.navigate(['commercial']);
            }
        );
    }

    getAllArticles(): void {
        this.articleService.getArticles().subscribe(
            article => this.articles = article,
            error => console.log(error)
        );
    }

    getFilteredArticles(query: string, city: string): void {
        this.articleService.getFilteredArticles(query, city).subscribe(
            article => this.articles = article,
            error => console.log(error)
        );
    }
}
