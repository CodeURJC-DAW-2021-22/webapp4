import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {Category} from 'src/app/models/category.model';
import {CategoryService} from '../../services/category.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'category',
    templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit {

    categories: Category[];

    category: Category;

    @ViewChild("file")
    file: any;

    removeImage: boolean;

    constructor(public categoryService: CategoryService, public loginService: LoginService, private router: Router) {
    }

    ngOnInit(): void {
        this.category = {id_CATEGORY: null, title: "", description: "", icon: "", categorys: null, size: 0};
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
        );
    }


    uploadImage(categoryImage: Category): void {

        const image = this.file.nativeElement.files[0];
        if (image) {
            let formData = new FormData();
            formData.append("imageFile", image);
            this.categoryService.setCategoryImage(categoryImage, formData).subscribe(
                _ => this.afterUploadImage(categoryImage),
                error => alert('Error uploading category image: ' + error)
            );
        } else {
            this.afterUploadImage(categoryImage);
        }
    }

    private afterUploadImage(afterCategoryImage: Category) {
        this.router.navigate(['/categoryList']);
    }

    save() {
        this.categoryService.addCategory(this.category).subscribe(
            (category: Category) => this.uploadImage(category),
            error => alert('Error creating new category: ' + error)
        );
    }
}
