import {Component} from '@angular/core';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'profile',
    templateUrl: './profile.component.html'
})
export class ProfileComponent {
    constructor(public loginService: LoginService) {
    }
}
