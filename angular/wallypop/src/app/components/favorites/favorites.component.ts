import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Article } from "src/app/models/article.model";
import { Favorites } from "src/app/models/favorites.model";
import { ArticleService } from "src/app/services/article.service";
import { FavoriteService } from "../../services/favorites.service";
import { LoginService } from "../../services/login.service";

@Component({
    selector: 'favorites',
    templateUrl: './favorites.component.html'
})
export class FavoritesComponent implements OnInit{

    favorites: Favorites[];

    constructor(public favoriteService: FavoriteService, public loginService: LoginService, private router: Router, private articleService: ArticleService) {
    }

    ngOnInit(): void {
        console.log(this.loginService.currentUser());
        this.favoriteService.getFavorites(this.loginService.currentUser().id_USER).subscribe(
            favorite => {this.favorites = favorite;
            console.log(favorite);
        },
            error => console.log(error)
          );
    }
    
    addFavorite(idArticle:number|string, idUser:number|string) {
        console.log('addFavorite');
        this.favoriteService.addFavorite(idArticle,idUser);
        console.log('addFavorite');
        this.router.navigate(['favorites']);
    }
}
