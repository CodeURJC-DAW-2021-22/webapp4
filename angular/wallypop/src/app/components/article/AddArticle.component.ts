import {Component} from '@angular/core';
//  ESTA LINEA HAY QUE DESCOMENTARLA CUANDO SE HAGA EL MERGE CON LA RAMA CATEGORY
//import {Category } from 'src/app/models/category.model';
import {ArticleService} from '../../services/article.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'AddArticle',
    templateUrl: './AddArticle.component.html'
})
export class AddArticle {
    constructor(public articleService: ArticleService, public loginService: LoginService) {
    }

    addArticle(event: any, title: string, description: string, city: string, price: number, postal_code: number): void {
        event.preventDefault();
        this.articleService.addArticle(title,description,city, price, postal_code);
    }
    /*
     * ESTE METODO HAY QUE DESCOMENTARLO CUANDO SE HAGA EL MERGE CON LA RAMA CATEGORY
    uploadCategories(event: any): Observable<Category[]> {
        event.preventDefault();
        this.articleService.addArticle(title,description,city, price, postal_code);
    }
    */
}
