import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Favorites } from "src/app/models/favorites.model";
import { FavoriteService } from "../../services/favorites.service";
import { LoginService } from "../../services/login.service";

@Component({
    selector: 'favorites',
    templateUrl: './favorites.component.html'
})
export class FavoritesComponent implements OnInit{

    favorites: Favorites[];

    constructor(public favoriteService: FavoriteService, public loginService: LoginService, private router: Router) {
    }

    ngOnInit(): void {
        this.favoriteService.getFavorites().subscribe(
            favorite => this.favorites = favorite,
            error => console.log(error)
          );
    }
    

}
