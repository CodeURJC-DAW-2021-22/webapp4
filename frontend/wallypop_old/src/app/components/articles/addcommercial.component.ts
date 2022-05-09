import {Component, OnInit, ViewChild} from '@angular/core';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {ArticleService} from '../../services/article.service';
import {Router} from '@angular/router';
import {Article} from '../../models/article.model';

@Component({
    selector: 'addcommercial',
    templateUrl: './addcommercial.component.html'
})
export class AddcommercialComponent implements OnInit {
    categorys: Category[];
    @ViewChild("file")
    file: any;

    ngOnInit(): void {
        this.categoryService.getCategories().subscribe(
            response => this.categorys = response,
            error => console.log(error)
        );
    }

    constructor(private categoryService: CategoryService, private articleService: ArticleService, private router: Router) {
    }

    // tslint:disable-next-line:variable-name
    save($event: any, TITLE: string, DESCRIPTION: string, CITY: string, PRICE: string, POSTAL_CODE: string, categories: any): void {
        const c = [1, 2, 3];
        this.articleService.addArticle(TITLE, DESCRIPTION, CITY, PRICE, POSTAL_CODE, c).subscribe(
            response => this.uploadImage(response as Article),
            error => console.log(error)
        );
    }

    uploadImage(articleImage: Article): void {

        const image = this.file.nativeElement.files[0];
        if (image) {
            const formData = new FormData();
            formData.append('imageFile', image);
            this.articleService.setArticleImage(articleImage, formData).subscribe(
                _ => this.afterUploadImage(articleImage),
                error => alert('Error uploading category image: ' + error)
            );
        } else {
            this.afterUploadImage(articleImage);
        }
    }

    private afterUploadImage(afterArticleImage: Article): void {
        this.router.navigate(['/commercial']);
    }
}
