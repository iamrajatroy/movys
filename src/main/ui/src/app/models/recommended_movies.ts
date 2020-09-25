import { Movie } from './movie';

export interface RecommendedMovies {
    likedMovieName: String;
	similarMovies: Movie[];
}