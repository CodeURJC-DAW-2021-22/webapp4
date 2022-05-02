import {Component, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import {CategoryService} from '../../services/category.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'category',
    templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit{

    categories: Category[];

    @ViewChild("file")
    file: any;

    removeImage:boolean;

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

    uploadImage(categoryImage: Category): void {

        const image = this.file.nativeElement.files[0];
        if (image) {
          let formData = new FormData();
          formData.append("imageField", image);
          this.categoryService.setCategoryImage(categoryImage, formData).subscribe(
            _ => this.afterUploadImage(categoryImage),
            error => alert('Error uploading category image: ' + error)
          );
        } else if(this.removeImage){
          this.categoryService.deleteCategoryImage(categoryImage).subscribe(
            _ => this.afterUploadImage(categoryImage),
            error => alert('Error deleting book image: ' + error)
          );
        } else {
          this.afterUploadImage(categoryImage);
        }
      }
    
      private afterUploadImage(afterCategoryImage: Category){
        this.router.navigate(['/category/', afterCategoryImage.id_CATEGORY]);
      }
}
