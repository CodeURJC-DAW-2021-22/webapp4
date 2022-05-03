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
import {CommercialComponent} from './components/articles/commercial.component';

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'newaccount', component: NewaccountComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'category', component: CategoryComponent},
    { path: 'formReport', component: FormReportComponent},
    { path: 'reports', component: ReportsComponent},
    { path: 'showReport', component: ShowReportComponent},
    { path: 'categoryList', component: CategoryListComponent},
    { path: 'commercial', component: CommercialComponent },
    { path: 'showReport/:id', component: ShowReportComponent},
    { path: '**', component: NotFoundComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
