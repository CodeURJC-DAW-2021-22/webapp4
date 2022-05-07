import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {User} from '../../models/user.model';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'post',
    templateUrl: './post.component.html'
})
export class PostComponent implements OnInit {
    article: Article;
    user: User;
    categories: Category[];
    idArticle: number;
    // tslint:disable-next-line:max-line-length
    constructor(private articleService: ArticleService, private categoryService: CategoryService, public loginService: LoginService, private routing: ActivatedRoute, private router: Router) {
        this.idArticle = -1;
    }

    ngOnInit(): void {
        this.getCategories();
        this.idArticle = this.routing.snapshot.params.id;
        if (this.idArticle !== -1) {
            this.getArticle(this.idArticle);
            /* if (this.article === undefined) {
                this.router.navigate(['commercial']);
            } */
        }
    }

    getCategories(): void {
        this.categoryService.getCategories().subscribe(
            category => this.categories = category,
            error => console.log(error)
        );
    }

    getArticle(id: number | string): void {
        this.articleService.getArticle(id).subscribe(
            article => this.article = article,
            error => console.log(error)
        );
        this.getUserArticle(id);
    }

    getUserArticle(id: number | string): void {
        this.articleService.getUserArticle(id).subscribe(
            user => this.user = user,
            error => console.log(error)
        );
    }

    isOwner(): boolean {
        return this.user.id_USER === this.loginService.currentUser().id_USER;
    }

    isAdmin(): boolean {
        return this.loginService.currentUser().is_ADMIN;
    }

    isReserved(): boolean {
        return this.article.reserved;
    }

    isSold(): boolean{
        return this.article.sold;
    }

    removeArticle(removeArticleSelected: Article): void {
        const okResponse = window.confirm('Do you want to remove this article?');
        if (okResponse) {
            this.articleService.deleteArticle(removeArticleSelected).subscribe(
                _ => this.router.navigate(['/commercial']),
                error => console.error(error)
            );
        }
    }
}
