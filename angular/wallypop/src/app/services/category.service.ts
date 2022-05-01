import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {Category} from '../models/category.model';
import {Router} from '@angular/router';

const BASE_URL = '/api/';

@Injectable({providedIn: 'root'})
export class CategoryService {

    constructor(private httpClient: HttpClient, private router: Router) {
    }

    static handleError(error: any): Observable<never> {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }

    getCategories(): Observable<Category[]> {
        return this.httpClient.get(BASE_URL + 'categories').pipe(
            catchError(error => CategoryService.handleError(error))
        ) as Observable<Category[]>;
    }

    getCategory(id: number | string): Observable<Category> {
        return this.httpClient.get(BASE_URL + 'categories/' + id).pipe(
            catchError(error => CategoryService.handleError(error))
        ) as Observable<Category>;
    }

    addCategory(title: string, description: string, icon: string): void {

        this.httpClient.post(BASE_URL + 'admin/categories', {title, description, icon}, {withCredentials: true})
            .subscribe(
                (response) => this.router.navigate(['profile']),
                (error) => alert('Usuario ya existe, inicie sesión')
            );
        /*return this.httpClient.post(BASE_URL + 'admin/categories', {title, description, icon}, {withCredentials: true})
            .pipe(
                catchError(error => CategoryService.handleError(error))
            );*/
    }

    setCategoryImage(category: Category, formData: FormData): Observable<any> {
        return this.httpClient.post(BASE_URL + 'admin/categories/' + category.id_CATEGORY + '/image', formData)
            .pipe(
                catchError(error => CategoryService.handleError(error))
            );
    }

    deleteCategoryImage(category: Category): Observable<any> {
        return this.httpClient.delete(BASE_URL + 'admin/categories/' + category.id_CATEGORY + '/image')
            .pipe(
                catchError(error => CategoryService.handleError(error))
            );
    }

    deleteCategory(category: Category): Observable<any> {
        return this.httpClient.delete(BASE_URL + 'admin/categories/' + category.id_CATEGORY).pipe(
            catchError(error => CategoryService.handleError(error))
        );
    }

    updateCategory(category: Category): Observable<any> {
        return this.httpClient.put(BASE_URL + 'admin/categories/' +  category.id_CATEGORY, category).pipe(
            catchError(error => CategoryService.handleError(error))
        );
    }
}
