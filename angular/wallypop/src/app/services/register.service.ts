import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import {Router} from '@angular/router';

const BASE_URL = '/api/users';

@Injectable({ providedIn: 'root' })
export class RegisterService {

    constructor(private http: HttpClient, private router: Router) {
    }

    register(fullName: string, email: string, tel: string, password: string): void {

        this.http.post(BASE_URL + '', { full_NAME: fullName, name: email, tel, password }, { withCredentials: true })
            .subscribe(
                (response) => this.router.navigate(['login']),
                (error) => alert('Usuario ya existe, inicie sesión')
            );
    }
}
