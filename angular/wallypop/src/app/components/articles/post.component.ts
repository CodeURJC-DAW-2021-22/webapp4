import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article.model';
import {Category} from '../../models/category.model';
import {CategoryService} from '../../services/category.service';
import {User} from '../../models/user.model';
import {ActivatedRoute, Router} from '@angular/router';
import { ReportService } from 'src/app/services/report.service';


@Component({
    selector: 'post',
    templateUrl: './post.component.html'
})
export class PostComponent implements OnInit {

    article: Article;
    user: User;
    categories: Category[];
    idArticle: number;
    emailSent = -1;
    // tslint:disable-next-line:max-line-length
    constructor(private reportService: ReportService, private articleService: ArticleService, private categoryService: CategoryService, public loginService: LoginService, public routing: ActivatedRoute, private router: Router) {
        this.idArticle = -1;
        this.emailSent = -1;
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
        this.routing.queryParams.subscribe(params => {
            if (params.e === '1') {
                this.emailSent = 1;
            }
            if (params.e === '0') {
                this.emailSent = 0;
            }
        });
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
            error => {
                alert('No existe este artículo o no está disponible en estos momentos');
                this.router.navigate(['commercial']);
            }
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

    newForm(): void {
        this.router.navigate(['/formReport/' + this.article.id_ARTICLE]);
      }

    // tslint:disable-next-line:variable-name
    sendEmail($event: any, message: string, id_USER: number, id_ARTICLE: number): void {
        this.articleService.sendEmail(message, id_USER, id_ARTICLE);
    }

    isSold(): boolean {
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
