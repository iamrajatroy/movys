package com.codeit.rajat.movys.controller;

import java.rmi.ServerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeit.rajat.movys.object.RecommendedMovies;
import com.codeit.rajat.movys.persistence.object.DBLikedMovie;
import com.codeit.rajat.movys.persistence.object.DBMovie;
import com.codeit.rajat.movys.service.MovieService;

import javassist.tools.rmi.ObjectNotFoundException;

/**
 * 
 * @author rajat
 * 
 *         API Controller for handling request from frontend
 * 
 */

@RestController
@RequestMapping("/movies_api")
public class MoviesApi
{

	@Autowired
	private MovieService movieService;

	/**
	 * called on clicking like button
	 * 
	 * adds liked movie into the database
	 * 
	 * @param movie
	 * @return string message
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> addLikedMovie(@RequestBody DBMovie movie)
	{
		Map<String, String> response = new HashMap<>();
		try
		{

			DBMovie savedLikedMovie = this.movieService.addLikedMovie(movie);
			response.put("message", "Hey! You just liked " + savedLikedMovie.getOriginalTitle());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		} catch (ServerException e)
		{
			e.printStackTrace();
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * Fetch a list of movies liked by users
	 * 
	 * @return list 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list_liked", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DBLikedMovie>> listLikedMovies()
	{
		List<DBLikedMovie> likedMovies = this.movieService.listLikedMoives();
		return new ResponseEntity<List<DBLikedMovie>>(likedMovies, HttpStatus.OK);
	}

	/**
	 * 
	 * Fetch all list of movies in the database
	 * 
	 * @return list
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DBMovie>> listMovies()
	{
		List<DBMovie> movies = this.movieService.listMovies();
		return new ResponseEntity<List<DBMovie>>(movies, HttpStatus.OK);
	}

	/**
	 * 
	 * Get a list of movies recommended to the user
	 * 
	 * @return list
	 * @throws ServerException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recommend", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecommendedMovies>> listRecommendedMovies() throws ServerException
	{
		List<RecommendedMovies> recommendedMovies = null;
		try
		{
			recommendedMovies = this.movieService.listRecommendedMovies();
		} catch (JSONException | ObjectNotFoundException e)
		{
			e.printStackTrace();
			throw new ServerException("Server error occured with message= " + e.getMessage());
		}
		return new ResponseEntity<List<RecommendedMovies>>(recommendedMovies, HttpStatus.OK);
	}

}
