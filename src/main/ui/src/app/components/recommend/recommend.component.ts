import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/services/movie.service';
import { RecommendedMovies } from 'src/app/models/recommended_movies';

@Component({
  selector: 'app-recommend',
  templateUrl: './recommend.component.html',
  styleUrls: ['./recommend.component.css']
})
export class RecommendComponent implements OnInit {

  movies: RecommendedMovies[] = [];

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.movieService.getRecommendedMovies()
      .subscribe(data => {
        this.movies = data
      });
  }

}
