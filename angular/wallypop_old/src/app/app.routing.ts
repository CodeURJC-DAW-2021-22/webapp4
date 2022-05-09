import { Routes, RouterModule } from '@angular/router';

import {IndexComponent} from './components/index/index.component';
import {LoginComponent} from './components/login/login.component';
import {NotFoundComponent} from './components/404/NotFound.component';
import {NewaccountComponent} from './components/login/newaccount.component';
import {ProfileComponent} from './components/profile/profile.component';
import { CategoryComponent } from './components/category/category.component';
import { FormReportComponent } from './components/report/formReport.component';
import { ReportsComponent } from './components/report/reports.component';
import { ShowReportComponent } from './components/report/showReport.component';
import { CategoryListComponent } from './components/category/categoryList.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import {CommercialComponent} from './components/articles/commercial.component';
import {PostComponent} from './components/articles/post.component';
import {YourcommercialComponent} from './components/articles/yourcommercial.component';
import {YourcommercialSoldComponent} from './components/articles/yourcommercialsold.component';
import {SoldComponent} from './components/articles/sold.component';
import {AddcommercialComponent} from './components/articles/addcommercial.component';

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'newaccount', component: NewaccountComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'category', component: CategoryComponent},
    { path: 'formReport/:id', component: FormReportComponent},
    { path: 'reports', component: ReportsComponent},
    { path: 'showReport', component: ShowReportComponent},
    { path: 'categoryList', component: CategoryListComponent},
    { path: 'favorites', component: FavoritesComponent},
    { path: 'commercial', component: CommercialComponent },
    { path: 'showReport/:id', component: ShowReportComponent},
    { path: 'commercial/:id', component: CommercialComponent },
    { path: 'post', component: PostComponent },
    { path: 'post/:id', component: PostComponent },
    { path: 'yourcommercial', component: YourcommercialComponent },
    { path: 'yourcommercialsold', component: YourcommercialSoldComponent },
    { path: 'sold', component: SoldComponent },
    { path: 'sold/:id', component: SoldComponent },
    { path: 'adcommercial', component: AddcommercialComponent },
    { path: '**', component: NotFoundComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
