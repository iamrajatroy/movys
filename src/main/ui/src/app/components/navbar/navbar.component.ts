import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MovieSearchService } from 'src/app/services/movie-search.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  active = 1;
  searchTerm: string;
  
  
  
  constructor(private movieSearch: MovieSearchService) { }

  ngOnInit(): void {
  }

  searchBarTyped() {
    console.log("NAV search"+this.searchTerm)
    this.movieSearch.searchBarTyped(this.searchTerm);
  }

}
