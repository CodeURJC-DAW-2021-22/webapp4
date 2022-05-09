import {Component, OnInit} from '@angular/core';
import {Category} from '../../models/category.model';
import {Router} from '@angular/router';
import {CategoryService} from '../../services/category.service';

@Component({
  selector: 'index',
  templateUrl: './index.component.html'
})
export class IndexComponent implements OnInit {

  categories: Category[];

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      category => this.categories = category,
      error => console.log(error)
    );
  }

}
