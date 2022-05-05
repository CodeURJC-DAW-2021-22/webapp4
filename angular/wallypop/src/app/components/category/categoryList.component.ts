import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import {CategoryService} from '../../services/category.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'categoryList',
    templateUrl: './categoryList.component.html'
})
export class CategoryListComponent implements OnInit{

    categories: Category[];

    constructor(public categoryService: CategoryService, public loginService: LoginService, private router: Router) {
    }
    ngOnInit(): void {
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
          );
    }

    addCategory(event: any, title: string, description: string, icon: string): void {
        event.preventDefault();
        this.categoryService.addCategory(title,description,icon);
    }

    removeCategory(removeCategorySelected: Category): void {
        const okResponse = window.confirm('Do you want to remove this category?');
        if (okResponse) {
            this.categoryService.deleteCategory(removeCategorySelected).subscribe(
                _ => this.router.navigate(['/category']),
                error => console.error(error)
            );
        }
    }

}