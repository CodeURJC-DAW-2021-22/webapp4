import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {User} from '../../models/user.model';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'commercial',
    templateUrl: './commercial.component.html'
})
export class CommercialComponent implements OnInit {
    articles: Article[];
    categories: Category[];
    category: Category;
    idCategory: number;
    constructor(private articleService: ArticleService, private categoryService: CategoryService, private loginService: LoginService, private routing: ActivatedRoute) {
        this.idCategory = -1;
    }

    ngOnInit(): void {
        this.getCategories();

        this.idCategory = this.routing.snapshot.params.id;
        if (this.idCategory !== undefined) {
            this.getArticlesFromCategory(this.idCategory);
        } else {
            this.getAllArticles();
        }
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
            error => console.log(error)
        );
    }

    getAllArticles(): void {
        this.articleService.getArticles().subscribe(
            article => this.articles = article,
            error => console.log(error)
        );
    }

}
