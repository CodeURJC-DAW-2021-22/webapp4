import {Component} from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'ArticlesUserPagination',
    templateUrl: './ArticlesUserPagination.component.html'
})
export class ArticlesUserPagination {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }
    
    listArticlesUserPagination(event: any, user: User): void {
        event.preventDefault();
        this.articleService.listArticlesUserPagination(user);
    }
  

}