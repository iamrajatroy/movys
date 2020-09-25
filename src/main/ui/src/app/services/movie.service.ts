import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Movie } from '../models/movie';
import { LikedMovie } from '../models/liked_movie';
import { RecommendedMovies } from '../models/recommended_movies';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private MOVIES_API_SERVER = "/movies_api";

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]>{
    return this.http.get<Movie[]>(this.MOVIES_API_SERVER+"/list");
  }

  getLikedMovies(): Observable<LikedMovie[]>{
    return this.http.get<LikedMovie[]>(this.MOVIES_API_SERVER+"/list_liked");
  }

  likeMovie(movie): Observable<Object>{
    var headers = new HttpHeaders();
    headers.set('Content-Type', "application/json")
    return this.http.post<Object>(this.MOVIES_API_SERVER+"/like", movie, {headers});
  }

  getRecommendedMovies(): Observable<RecommendedMovies[]>{
    return this.http.get<RecommendedMovies[]>(this.MOVIES_API_SERVER+"/recommend");
  }

}
