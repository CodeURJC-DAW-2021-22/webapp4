import {Component} from '@angular/core';
import {RegisterService} from '../../services/register.service';

@Component({
    selector: 'NewAccount',
    templateUrl: './newaccount.component.html'
})
export class NewaccountComponent {
    constructor(public registerService: RegisterService) {
    }

    register(event: any, fullName: string, email: string, tel: string, password: string): void {
        event.preventDefault();
        this.registerService.register(fullName, email, tel, password);
    }
}
