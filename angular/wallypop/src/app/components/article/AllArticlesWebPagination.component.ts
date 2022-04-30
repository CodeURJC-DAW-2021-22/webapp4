

import {Component} from '@angular/core';
import { Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'AllArticlesWebPagination',
    templateUrl: './AllArticlesWebPagination.component.html'
})
export class AllArticlesWebPagination {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }
    
    listAllArticlesWebPagination(event: any,): void {
        event.preventDefault();
        this.articleService.listAllArticlesWebPagination();
    }
}
  