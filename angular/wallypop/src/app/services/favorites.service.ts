import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import { Favorites } from '../models/favorites.model';
import { Article } from '../models/article.model';

const BASE_URL = '/api/';

@Injectable({providedIn: 'root'})
export class FavoriteService {

    constructor(private httpClient: HttpClient, private router: Router) {
    }

    static handleError(error: any): Observable<never> {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }

    getFavorites(idUser: number|string): Observable<Favorites[]> {
        return this.httpClient.get(BASE_URL + 'favorites/' + idUser).pipe(
            catchError(error => FavoriteService.handleError(error))
        ) as Observable<Favorites[]>;
    }

    getFavorite(idUser: number|string, idArticle: number|string): Observable<Favorites> {
        return this.httpClient.get(BASE_URL + 'favorites/' + idUser + '/' + idArticle).pipe(
            catchError(error => FavoriteService.handleError(error))
        ) as Observable<Favorites>;
    }

    addFavorite(idUser: number|string, idArticle: number|string) {
        return this.httpClient.post(BASE_URL + 'favorites/' + idUser + '/' + idArticle, null)
            .pipe(
                catchError(error => FavoriteService.handleError(error))
            );
    }

    deleteFavorite(favorite: Favorites): Observable<any> {
        return this.httpClient.delete(BASE_URL + 'favorites/' + favorite.id_FAVORITE).pipe(
            catchError(error => FavoriteService.handleError(error))
        );
    }
}

