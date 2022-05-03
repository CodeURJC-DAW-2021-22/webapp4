import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { IndexComponent } from './components/index/index.component';
import { routing } from './app.routing';
import { LoginComponent } from './components/login/login.component';
import {SearchComponent} from './components/search/search.component';
import {NotFoundComponent} from './components/404/NotFound.component';
import {NewaccountComponent} from './components/login/newaccount.component';
import { ProfileComponent } from './components/profile/profile.component';
import { CategoryComponent } from './components/category/category.component';

import { FormReportComponent } from './components/report/formReport.component';
import { ReportsComponent } from './components/report/reports.component';
import { ShowReportComponent } from './components/report/showReport.component';
import { CategoryListComponent } from './components/category/categoryList.component';
import {CommercialComponent} from './components/articles/commercial.component';

@NgModule({
  declarations: [AppComponent, IndexComponent, LoginComponent, SearchComponent, NotFoundComponent, NewaccountComponent, ProfileComponent, CategoryComponent, CategoryListComponent, CommercialComponent, FormReportComponent, ReportsComponent, ShowReportComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, routing],
  bootstrap: [AppComponent]
})
export class AppModule { }
