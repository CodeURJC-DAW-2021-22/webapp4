import {Injectable} from '@angular/core';
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

    getFavorites(): Observable<Favorites[]> {
        return this.httpClient.get(BASE_URL + 'favorites').pipe(
            catchError(error => FavoriteService.handleError(error))
        ) as Observable<Favorites[]>;
    }

    getFavorite(id: number | string): Observable<Favorites> {
        return this.httpClient.get(BASE_URL + 'favorites/' + id).pipe(
            catchError(error => FavoriteService.handleError(error))
        ) as Observable<Favorites>;
    }

    addFavorite(idArticle:number|string, idUser:number|string) {
			return this.httpClient.post(BASE_URL + "favorites/" + idUser + "/" + idArticle,"")
				.pipe(
					catchError(error => FavoriteService.handleError(error))
				);
    }


    deleteFavorite(favorite: Favorites): Observable<any> {
        return this.httpClient.delete(BASE_URL + 'favorites/' + favorite.id_CATEGORY).pipe(
            catchError(error => FavoriteService.handleError(error))
        );
    }
}