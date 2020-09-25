package com.codeit.rajat.movys.service;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeit.rajat.movys.object.RecommendedMovies;
import com.codeit.rajat.movys.persistence.LikedMovieRepository;
import com.codeit.rajat.movys.persistence.MovieRepository;
import com.codeit.rajat.movys.persistence.object.DBLikedMovie;
import com.codeit.rajat.movys.persistence.object.DBMovie;

import javassist.tools.rmi.ObjectNotFoundException;

/**
 * 
 * @author rajat
 * 
 *         Internal logic
 * 
 *         All requests coming from Controller (MoviesApi.class) are handled
 *         here
 *
 */

@Service
public class MovieService
{

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private LikedMovieRepository likedMovieRepository;

	@Autowired
	private RecommenderServiceCaller recommenderService;

	public MovieService()
	{
		super();
	}

	/**
	 * Add movie to db
	 * 
	 * Called during app is initialized. (See AppInitializr.class)
	 * 
	 * 
	 * @param movie
	 * @return movie
	 */
	public DBMovie addMovie(DBMovie movie)
	{
		return this.movieRepository.save(movie);
	}

	/**
	 * fetch list of movies from db
	 * 
	 * @return list
	 */
	public List<DBMovie> listMovies()
	{
		List<DBMovie> movies = new ArrayList<>();
		this.movieRepository.findAll().forEach(movies::add);
		return movies;
	}

	/**
	 * 
	 * Add liked movie to db
	 * 
	 * @param movie
	 * @return
	 * @throws ServerException
	 */
	public DBMovie addLikedMovie(DBMovie movie) throws ServerException
	{
		DBLikedMovie exchangeObj = new DBLikedMovie();
		exchangeObj.setId(movie.getId());
		exchangeObj.setMovie(movie);
		DBLikedMovie savedMovie = this.likedMovieRepository.save(exchangeObj);
		if (savedMovie == null)
			throw new ServerException("Failed to add liked movie. " + movie.getOriginalTitle());
		return savedMovie.getMovie();
	}

	/**
	 * 
	 * fetch list of liked movies from db
	 * 
	 * @return list
	 */
	public List<DBLikedMovie> listLikedMoives()
	{
		List<DBLikedMovie> likedMovies = new ArrayList<>();
		this.likedMovieRepository.findAll().forEach(likedMovies::add);
		return likedMovies;
	}

	/**
	 * 
	 * get a list of liked movies and prepare request for Recommended movies
	 * 
	 * @return list
	 * @throws JSONException
	 * @throws ObjectNotFoundException
	 */
	public List<RecommendedMovies> listRecommendedMovies() throws JSONException, ObjectNotFoundException
	{
		List<RecommendedMovies> recommendedMovies = new ArrayList<>();
		List<DBLikedMovie> likedMovies = this.listLikedMoives();
		if (likedMovies.size() == 0)
			throw new ObjectNotFoundException("You have not liked any movies yet.");
		for (DBLikedMovie likedMovie : likedMovies)
		{
			DBMovie topMovie = likedMovie.getMovie();
			if (topMovie.getOriginalTitle() == null)
				continue;
			// call to recommender service here
			JSONObject recommendation = recommenderService.getRecommendation(topMovie.getOriginalTitle());
			JSONArray similarMovies = recommendation.getJSONArray("similar_movies");
			List<DBMovie> simMovies = new ArrayList<>();
			for (int i = 0; i < similarMovies.length(); i++)
			{
				String title = (String) similarMovies.get(i);
				DBMovie movieByTitle = getMovieByTitle(title);

				if (movieByTitle != null)
				{
					simMovies.add(this.getMovieByTitle(title));
				} else
				{
					throw new ObjectNotFoundException("Similar movie not found with name= " + title);
				}

			}
			RecommendedMovies recommendedMovie = new RecommendedMovies();
			recommendedMovie.setLikedMovieName(topMovie.getOriginalTitle());
			recommendedMovie.setDbMovie(simMovies);
			recommendedMovies.add(recommendedMovie);
		}
		return recommendedMovies;
	}

	/**
	 * Helper function to get movie object by title
	 * 
	 * @param title
	 * @return
	 */
	private DBMovie getMovieByTitle(String title)
	{
		List<DBMovie> movies = this.listMovies();
		for (DBMovie movie : movies)
		{
			if (title.equalsIgnoreCase(movie.getOriginalTitle()))
				return movie;
		}
		return null;
	}
}
