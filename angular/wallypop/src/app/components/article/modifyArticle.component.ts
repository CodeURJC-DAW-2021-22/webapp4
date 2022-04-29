import {Component} from '@angular/core';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'modifyArticle',
    templateUrl: 'modifyArticle.component.html'
})
export class modifyArticleComponent {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }
    
    deleteArticle(event: any, article: Article): void {
        event.preventDefault();
        this.articleService.deleteArticle(article);
    }
    
    updateArticle(event: any, article: Article): void {
        event.preventDefault();
        this.articleService.updateArticle(article);
    }
}
