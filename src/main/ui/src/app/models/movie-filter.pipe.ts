import { PipeTransform, Pipe } from '@angular/core';
import { Movie } from './movie';

@Pipe({
    name: 'movieFilter'
})
export class MovieFilterPipe implements PipeTransform {
    transform(movies: Movie[], searchTerm: string): Movie[] {
        if (!movies || !searchTerm) {
            return movies;
        }
        return movies.filter(movie =>
            movie.originalTitle.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1);
    }
}