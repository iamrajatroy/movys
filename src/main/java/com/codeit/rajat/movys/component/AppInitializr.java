package com.codeit.rajat.movys.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.codeit.rajat.movys.persistence.object.DBMovie;
import com.codeit.rajat.movys.service.MovieService;
import com.codeit.rajat.movys.service.RecommenderServiceCaller;

/**
 * 
 * @author rajat
 * 
 *         Initializer class. Loads movies data from csv file to MySQL database.
 */
@Component
public class AppInitializr
{
	@Autowired
	private MovieService movieService;

	@Autowired
	private RecommenderServiceCaller recommenderService;

	@PostConstruct
	public void init() throws IOException, ParseException, URISyntaxException
	{

		// checking model api service

		System.out.println("Checking model api service.");

		try
		{
			ResponseEntity<String> status = recommenderService.healthCheck();

			if (status.getStatusCode().equals(HttpStatus.OK))
				System.out.println("Model Api service ready.");

			// import movies csv to db

			System.out.println("Loading database. Please wait!");

			InputStream stream = this.getClass().getResourceAsStream("/db/tmdb_movies_may_20_clean.csv");

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			int ctr = 0;
			while (reader.read() > -1)
			{
				try
				{
					String line = reader.readLine();
					if (ctr == 0)
					{
						// skip headers
						ctr++;
						continue;
					}
					String[] values = line.split(",");
					DBMovie movie = new DBMovie();
					movie.setOriginalTitle(values[1]);
					movie.setPopularity(Double.parseDouble(values[2]));
					movie.setAdult(Boolean.parseBoolean(values[3]));
					movie.setOverview(values[4]);
					String releaseDate = values[5];
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
					movie.setReleaseDate(date);
					movie.setPosterPath(values[6]);
					try
					{
						String genres = values[7];
						movie.setGenres(genres);
					} catch (IndexOutOfBoundsException e)
					{
						movie.setGenres("");
					}
					DBMovie addedMovie = this.movieService.addMovie(movie);
					if (addedMovie == null)
						System.out.println("Failed to add movie.");
					else
//				System.out.println("Added " + ctr + " - " + addedMovie.toString());
						ctr++;
				} catch (Exception e)
				{
					continue;
				}
			}
			ctr--;
//		System.out.println("Movies loaded to db. Total Rows=" + ctr);
			System.out.println("Db is ready.");
			reader.close();
		} catch (ServerException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

}
