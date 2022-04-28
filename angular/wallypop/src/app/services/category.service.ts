import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Category } from '../models/category.model';

const BASE_URL = '/api/admin/categories/';

@Injectable({ providedIn: 'root' })
export class CategoryService {

	constructor(private httpClient: HttpClient) { }

	getCategories(): Observable<Category[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Category[]>;
	}

	getCategory(id: number | string): Observable<Category> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Category>;
	}

	addCategory(title:string,description:string,icon:string) {

		return this.httpClient.post(BASE_URL + '', { title,description,icon })
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	setCategoryImage(category: Category, formData: FormData) {
		return this.httpClient.post(BASE_URL + category.id_CATEGORY + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteCategoryImage(category: Category) {
		return this.httpClient.delete(BASE_URL + category.id_CATEGORY + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteCategory(category: Category) {
		return this.httpClient.delete(BASE_URL + category.id_CATEGORY).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateCategory(category: Category) {
		return this.httpClient.put(BASE_URL + category.id_CATEGORY, category).pipe(
			catchError(error => this.handleError(error))
		);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}