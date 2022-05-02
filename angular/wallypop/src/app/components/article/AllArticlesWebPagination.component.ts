import {Component} from '@angular/core';
import {Observable, throwError} from 'rxjs';
//ESTA LINEA HAY QUE DESCOMENTARLA CUANDO SE HAGA EL MERGE CON LA RAMA CATEGORY
//import {Category } from 'src/app/models/category.model';
import {Article } from 'src/app/models/article.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'AllArticlesWebPagination',
    templateUrl: './AllArticlesWebPagination.component.html'
})
export class AllArticlesWebPagination {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }
    
    listAllArticlesWebPagination(event: any,): Observable<Object> {
        event.preventDefault();
        return this.articleService.listAllArticlesWebPagination();
    }
    
    /*
     * ESTE METODO HAY QUE DESCOMENTARLO CUANDO SE HAGA EL MERGE CON LA RAMA CATEGORY
    showCategories(event: any): Observable<Category[]> {
        event.preventDefault();
        this.articleService.addArticle(title,description,city, price, postal_code);
    }
    */
}
  