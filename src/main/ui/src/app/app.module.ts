import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import {AppRoutingModule, routingComponents} from './app-routing.module';

import { MovieService } from './services/movie.service';
import { MovieFilterPipe } from './models/movie-filter.pipe';
import { MovieSearchService } from './services/movie-search.service';





@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    routingComponents,
    MovieFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ScrollingModule,
    FormsModule
  ],
  providers: [MovieService,
    MovieSearchService],
  bootstrap: [AppComponent]
})




export class AppModule { }
