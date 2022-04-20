import { Routes, RouterModule } from '@angular/router';

import {IndexComponent} from "./components/index/index.component";
import {LoginComponent} from "./components/login/login.component";
import {NotFoundComponent} from "./components/404/NotFound.component";
import {NewaccountComponent} from "./components/login/newaccount.component";

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'newaccount', component: NewaccountComponent },
    { path: '**', component: NotFoundComponent}
]

export const routing = RouterModule.forRoot(appRoutes);