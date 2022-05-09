import {Component, OnInit} from '@angular/core';
import { LoginService } from './services/login.service';
import {User} from './models/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
    constructor(public loginService: LoginService) { }

    logOut(): void {
        this.loginService.logOut();
    }
}
