import { Injectable, EventEmitter, Output } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class MovieSearchService {

    @Output() movieSearched: EventEmitter<string> = new EventEmitter();

    constructor() { }

    searchBarTyped(searchTerm: string) {
        this.movieSearched.emit(searchTerm);
    }

    getEmitter() {
        return this.movieSearched;
    }


}
