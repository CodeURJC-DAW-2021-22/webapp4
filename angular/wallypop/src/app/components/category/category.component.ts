import {Component, OnInit} from '@angular/core';
import { Category } from 'src/app/models/category.model';
import {CategoryService} from '../../services/category.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'category',
    templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit{

    categories: Category[];

    constructor(public categoryService: CategoryService, public loginService: LoginService) {
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
}
