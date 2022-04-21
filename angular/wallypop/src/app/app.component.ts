import { Component } from '@angular/core';
import { LoginService } from './services/login.service';

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
