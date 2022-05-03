import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {User} from '../../models/user.model';

@Component({
    selector: 'commercial',
    templateUrl: './commercial.component.html'
})
export class CommercialComponent implements OnInit {
    articles: Article[];
    categories: Category[];
    constructor(private articleService: ArticleService, private categoryService: CategoryService, private loginService: LoginService) {
    }

    ngOnInit(): void {
        this.getCategories();
        this.getArticles();
    }

    getCategories(): void {
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
        );
    }

    getArticles(): void {
        this.articleService.getArticles().subscribe(
            article => this.articles = article,
            error => console.log(error)
        );
        this.articles.forEach(value => {
            value.user = this.getUser(value.userID);
        });
    }

    getUser(id: number): User {
        return this.loginService.getUser(id);
    }
}
