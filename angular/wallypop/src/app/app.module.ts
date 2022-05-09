import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {IndexComponent} from './components/index/index.component';
import {routing} from './app.routing';
import {LoginComponent} from './components/login/login.component';
import {SearchComponent} from './components/search/search.component';
import {NotFoundComponent} from './components/404/NotFound.component';
import {NewaccountComponent} from './components/login/newaccount.component';
import {ProfileComponent} from './components/profile/profile.component';
import {CategoryComponent} from './components/category/category.component';

import {FormReportComponent} from './components/report/formReport.component';
import {ReportsComponent} from './components/report/reports.component';
import {ShowReportComponent} from './components/report/showReport.component';
import {CategoryListComponent} from './components/category/categoryList.component';
import {FavoritesComponent} from './components/favorites/favorites.component';
import {CommercialComponent} from './components/articles/commercial.component';
import {PostComponent} from './components/articles/post.component';
import {YourcommercialComponent} from './components/articles/yourcommercial.component';
import {YourcommercialSoldComponent} from './components/articles/yourcommercialsold.component';
import {SoldComponent} from './components/articles/sold.component';
import {AddcommercialComponent} from './components/articles/addcommercial.component';
import {GraphicComponent} from './components/graphic/graphic.component';
import {HighchartsChartModule} from "highcharts-angular";

@NgModule({
    // tslint:disable-next-line:max-line-length
    declarations: [AppComponent, IndexComponent, LoginComponent, SearchComponent, NotFoundComponent, NewaccountComponent, ProfileComponent, CategoryComponent, CategoryListComponent, CommercialComponent, PostComponent, FormReportComponent, ReportsComponent, ShowReportComponent, FavoritesComponent, YourcommercialComponent, YourcommercialSoldComponent, SoldComponent, AddcommercialComponent, GraphicComponent],
    imports: [BrowserModule, FormsModule, HttpClientModule, routing, HighchartsChartModule],
    bootstrap: [AppComponent]
})
export class AppModule {
}
