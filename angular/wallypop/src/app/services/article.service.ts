import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import { User } from 'src/app/models/user.model';
import {Article} from '../models/article.model';
import {Router} from '@angular/router';

const BASE_URL = '/api/';

@Injectable({providedIn: 'root'})
export class ArticleService {

    constructor(private httpClient: HttpClient, private router: Router) {
    }

    getArticles(): Observable<Article[]> {
        return this.httpClient.get(BASE_URL).pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article[]>;
    }

    getArticle(id: number | string): Observable<Article> {
        return this.httpClient.get(BASE_URL + id).pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article>;
    }
    
    

    addArticle(title: string, description: string, city: string, price: number, postal_code: number) {

        this.httpClient.post(BASE_URL + 'admin/articles', {title, description, city, price, postal_code}, {withCredentials: true})
            .subscribe(
                (response) => this.router.navigate(['profile']),
                (error) => alert('Usuario ya existe, inicie sesi�n')
            );
    }

    setArticleImage(article: Article, formData: FormData) {
        return this.httpClient.post(BASE_URL + article.id_ARTICLE + '/image', formData)
            .pipe(
                catchError(error => this.handleError(error))
            );
    }

    deleteArticleImage(article: Article) {
        return this.httpClient.delete(BASE_URL + article.id_ARTICLE + '/image')
            .pipe(
                catchError(error => this.handleError(error))
            );
    }

    deleteArticle(article: Article) {
        return this.httpClient.delete(BASE_URL + article.id_ARTICLE).pipe(
            catchError(error => this.handleError(error))
        );
    }

    updateArticle(article: Article) {
        return this.httpClient.put(BASE_URL + article.id_ARTICLE, article).pipe(
            catchError(error => this.handleError(error))
        );
    }
    
    getIdArticle (article: Article){
        return this.httpClient.get(BASE_URL + article.id_ARTICLE).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }

    getDescription (article: Article){
        return this.httpClient.get(BASE_URL + article.description).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }
    
    getTitle (article: Article){
        return this.httpClient.get(BASE_URL + article.title).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }
    
    getPrice (article: Article){
        return this.httpClient.get(BASE_URL + article.price).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<number>;
    }
    
    getCity (article: Article){
        return this.httpClient.get(BASE_URL + article.city).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }
    
    getPostalCode (article: Article){
        return this.httpClient.get(BASE_URL + article.postal_code).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }

    private handleError(error: any) {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }
    
    listArticlesUserPagination (user: User){
        return this.httpClient.get(BASE_URL + user.articles).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
    }
    
    listAllArticlesWebPagination (){
        return this.httpClient.get(BASE_URL + this.getArticles).pipe(
                catchError(error => this.handleError(error))
            ) as Observable<string>;
            
    }
    
    /*
     * AQU� LA PAGINACI�N QUE HICIMOS PARA LA API REST. HAY QUE TRADUCIR ESTE M�TODO DE JAVA A TYPESCRIPT
    @GetMapping(params = {"page"})
    public ResponseEntity<List<Article>> articlesPagination(HttpServletRequest request, @RequestParam("page") int page) {
        if (page != -1) { // with pagination
            
            int pageSize = 4;
            try {
                List<Article> lArticlesPageable = new LinkedList<>();
                Pageable paging = PageRequest.of(0, pageSize);
                Page<Article> articlePage;
                articlePage = articleService.findAllPageable(paging.withPage(page));
                if (articlePage.getNumberOfElements() == 0) {
                    // Empty
                } else {
                    for (Article a : articlePage) {
                        lArticlesPageable.add(a);
                    }
                }
                return new ResponseEntity<>(lArticlesPageable, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else { // without pagination
            try {
                return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
*/
    
    
}
