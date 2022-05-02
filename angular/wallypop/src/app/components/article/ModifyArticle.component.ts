import {Component} from '@angular/core';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'ModifyArticle',
    templateUrl: 'ModifyArticle.component.html'
})
export class ModifyArticle {
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
   /**
 ESTE METODO HAY QUE DESCOMENTARLO CUANDO SE HAGA EL MERGE CON LA RAMA CATEGORY
    getCategoriesArticle(event: any, article: Article): void {
        event.preventDefault();
        this.articleService.getCategoriesArticle(article);
    }
    */
}
