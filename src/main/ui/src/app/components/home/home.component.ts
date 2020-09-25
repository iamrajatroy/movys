import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/services/movie.service';
import { Movie } from 'src/app/models/movie';
import { LikedMovie } from 'src/app/models/liked_movie';
import { Router } from '@angular/router'
import { MovieSearchService } from 'src/app/services/movie-search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  movies: Movie[] = [];
  liked_movies: LikedMovie[] = [];
  searchTerm: string;

  constructor(private movieService: MovieService, private router: Router,
    private movieSearch: MovieSearchService) { }

  ngOnInit(): void {
    // condition below will stop the multiple getMovies service call to save TTL
    if (this.movies.length == 0) {
      this.movieService.getMovies()
        .subscribe(data => {
          // for (var i = 0; i < 30; i++) {
          //   this.movies.push(data[i])
          // }
          this.movies = data;
        });
    }

    this.movieService.getLikedMovies()
      .subscribe(data => {
        this.liked_movies = data
      })

    this.movieSearch.getEmitter().subscribe(data => {
      console.log("SEARCH: "+data)
      this.searchTerm = data
    })

  }

  hitLike(movie) {
    this.movieService.likeMovie(movie).subscribe(data => {
      console.log(data);
      this.ngOnInit();
    })
  }

  isLiked(movie) {
    var id = movie['id']
    for (var i = 0; i < this.liked_movies.length; i++) {
      var liked_id = this.liked_movies[i]['id']
      if (id === liked_id) {
        return true;
      }
    }
    return false;
  }

}
