import { Component } from '@angular/core';
import {LoginService} from '../../services/login.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  constructor(public loginService: LoginService) { }

  logIn(event: any, email: string, password: string): void {

    event.preventDefault();

    this.loginService.logIn(email, password);
  }

  logOut(): void {
    this.loginService.logOut();
  }

}
