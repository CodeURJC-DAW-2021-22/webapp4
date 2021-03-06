import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Favorites} from '../models/favorites.model';

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

    getFavorites(idUser: number | string): Observable<Favorites[]> {
        return this.httpClient.get(BASE_URL + 'favorites/' + idUser).pipe(
            catchError(error => FavoriteService.handleError(error))
        ) as Observable<Favorites[]>;
    }

    addFavorite(idUser: number | string, idArticle: number | string): void {
        this.httpClient.post(BASE_URL + 'favorites/' + idUser + '/' + idArticle, null).subscribe(
            response => this.router.navigate(['favorites']),
            error => this.router.navigate(['favorites'])
        );
    }
}

