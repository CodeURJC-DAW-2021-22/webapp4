import {Component} from '@angular/core';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'article',
    templateUrl: './addArticle.component.html'
})
export class ArticleComponent {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }

    addArticle(event: any, title: string, description: string, city: string, price: number, postal_code: number): void {
        event.preventDefault();
        this.articleService.addArticle(title,description,city, price, postal_code);
    }
}
