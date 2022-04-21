import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import {Subscription} from 'rxjs';

const BASE_URL = '/api/auth';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean;
    user: User;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged(): void {

        this.http.get('/api/users', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                this.logged = true;
            },
            error => {
                if (error.status !== 404) {
                    console.error('Error inicio sesión: ' + JSON.stringify(error));
                }
            }
        );

    }

    logIn(email: string, pass: string): void {

        this.http.post(BASE_URL + '/login', { email, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => this.reqIsLogged(),
                (error) => alert('Usuario y/o contraseña no válidos')
            );

    }

    logOut(): Subscription{

        return this.http.post(BASE_URL + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log('Sesión finalizada con éxito');
                this.logged = false;
                this.user = undefined;
            });

    }

    isLogged(): boolean {
        return this.logged;
    }

    isAdmin(): boolean {
        return this.user.is_ADMIN;
    }

    currentUser(): User {
        return this.user;
    }
}
