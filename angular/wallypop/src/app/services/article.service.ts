import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import { User } from 'src/app/models/user.model';
import {Article} from '../models/article.model';
import {Router} from '@angular/router';
import {Category} from '../models/category.model';

import {CategoryService} from '../services/category.service';


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
/*
    getCategoriesArticle(article: Article): Observable<Category[]> {
        return this.httpClient.get(BASE_URL + article.categories).pipe(
            catchError(error => CategoryService.handleError(error))
        ) as Observable<Category[]>;
    } */

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
}
