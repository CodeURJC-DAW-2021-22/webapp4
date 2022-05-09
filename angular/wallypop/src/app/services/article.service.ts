import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {User} from 'src/app/models/user.model';
import {Article} from '../models/article.model';
import {Router} from '@angular/router';


const BASE_URL = '/api/';

@Injectable({providedIn: 'root'})
export class ArticleService {

    constructor(private httpClient: HttpClient, private router: Router) {
    }

    getArticles(): Observable<Article[]> {
        return this.httpClient.get(BASE_URL + 'articles').pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article[]>;
    }


    getArticle(id: number | string): Observable<Article> {
        return this.httpClient.get(BASE_URL + 'articles/' + id).pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article>;
    }

    getUserArticle(id: number | string): Observable<User> {
        return this.httpClient.get(BASE_URL + 'articles/user/' + id).pipe(
            catchError(error => this.handleError(error))
        ) as Observable<User>;
    }

    // tslint:disable-next-line:variable-name
    addArticle(TITLE: string, DESCRIPTION: string, CITY: string, p: string, POSTAL_CODE: string, categories: number[]) {
        // tslint:disable-next-line:max-line-length
        const PRICE = Number.parseFloat(p);
        return this.httpClient.post(BASE_URL + 'articles', {
            TITLE,
            DESCRIPTION,
            CITY,
            PRICE,
            POSTAL_CODE,
            categories
        }, {withCredentials: true});
    }

    setArticleImage(article: Article, formData: FormData) {
        return this.httpClient.post(BASE_URL + 'articles/' + article.id_ARTICLE + '/image', formData)
            .pipe(
                catchError(error => this.handleError(error))
            );
    }

    deleteArticle(article: Article) {
        return this.httpClient.delete(BASE_URL + 'articles/' + article.id_ARTICLE).pipe(
            catchError(error => this.handleError(error))
        );
    }


    private handleError(error: any): Observable<never> {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }

    getFilteredArticles(query: string, city: string): Observable<Article[]> {
        return this.httpClient.get(BASE_URL + 'articles/search?query=' + query + '&city=' + city).pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article[]>;
    }

    sendEmail(message: string, from: number, articleId: number): void {
        this.httpClient.post(BASE_URL + 'mail/' + from + '/' + articleId, {message}, {withCredentials: true}).subscribe(
            (response) => this.router.navigate(['/post/' + articleId], {queryParams: {e: 1}}),
            (error) => this.router.navigate(['/post/' + articleId], {queryParams: {e: 0}})
        );
    }

    reserve(idArticle: number): void {
        this.httpClient.post(BASE_URL + 'articles/' + idArticle + '/reserve', null).subscribe(
            response => location.reload(),
            error => console.log(error)
        );
    }

    sell(idArticle: number): void {
        this.httpClient.post(BASE_URL + 'articles/' + idArticle + '/sell', null).subscribe(
            response => location.reload(),
            error => console.log(error)
        );
    }
}
