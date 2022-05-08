import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from 'src/app/models/article.model';
import { Favorites } from 'src/app/models/favorites.model';
import { ArticleService } from 'src/app/services/article.service';
import { FavoriteService } from '../../services/favorites.service';
import { LoginService } from '../../services/login.service';
import {User} from '../../models/user.model';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

const BASE_URL = '/api/';

@Component({
    selector: 'favorites',
    templateUrl: './favorites.component.html'
})
export class FavoritesComponent implements OnInit{

    favorites: Favorites[];
    user: User;
    articles = [];

    constructor(public favoriteService: FavoriteService, public loginService: LoginService, private router: Router, private articleService: ArticleService, private httpClient: HttpClient) {
    }

    ngOnInit(): void {
        this.httpClient.get('/api/users', { withCredentials: true }).subscribe(
            user => {
                this.user = user as User;
                this.favoriteService.getFavorites(this.loginService.currentUser().id_USER).subscribe(
                    favorite => {
                        this.favorites = favorite as Favorites[];
                        favorite.forEach(value => {
                            this.getArticleFavorite(value.id_FAVORITE).subscribe(
                                response => {
                                    this.articles.push(response);
                                    this.favorites[this.favorites.indexOf(value)].article = response as Article;
                                }
                            );
                        });
                    },
                    error => console.log(error)
                );
            },
            error => {
                if (error.status !== 404) {
                    console.error('Error usuario: ' + JSON.stringify(error));
                }
            }
        );

    }

    getArticleFavorite(idFavorite: number | string): Observable<Article> {
        return this.httpClient.get(BASE_URL + 'favorites/' + idFavorite + '/article').pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Article>;
    }

    getUserFavorite(idFavorite: number | string): Observable<User> {
        return this.httpClient.get(BASE_URL + 'favorites/' + idFavorite + '/user').pipe(
            catchError(error => this.handleError(error))
        ) as Observable<User>;
    }

    refresh(): void {
        window.location.reload();
    }

    addFavorite(idArticle: number|string, idUser: number|string): void {
        this.favoriteService.addFavorite(idArticle, idUser);
        this.refresh();

    }

    private handleError(error: any): Observable<never> {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }
}
