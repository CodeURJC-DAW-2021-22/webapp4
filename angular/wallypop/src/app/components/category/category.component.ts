import {Component} from '@angular/core';
import { Category } from 'src/app/models/category.model';
import {CategoryService} from '../../services/category.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'category',
    templateUrl: './category.component.html'
})
export class CategoryComponent {
    constructor(public categoryService: CategoryService, public loginService: LoginService) {
    }

    addCategory(event: any, title: string, description: string, icon: string): void {
        event.preventDefault();
        this.categoryService.addCategory(title,description,icon);
    }
}
