import { Routes, RouterModule } from '@angular/router';

import {IndexComponent} from './components/index/index.component';
import {LoginComponent} from './components/login/login.component';
import {NotFoundComponent} from './components/404/NotFound.component';
import {NewaccountComponent} from './components/login/newaccount.component';
import {ProfileComponent} from './components/profile/profile.component';
import { CategoryComponent } from './components/category/category.component';
import { CategoryListComponent } from './components/category/categoryList.component';
import {CommercialComponent} from './components/articles/commercial.component';
import {PostComponent} from './components/articles/post.component';

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'newaccount', component: NewaccountComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'category', component: CategoryComponent},
    { path: 'categoryList', component: CategoryListComponent},
    { path: 'commercial', component: CommercialComponent },
    { path: 'post', component: PostComponent },
    { path: '**', component: NotFoundComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
