package com.codeit.rajat.movys.object;

import java.util.List;

import com.codeit.rajat.movys.persistence.object.DBMovie;

public class RecommendedMovies
{
	private String likedMovieName;
	private List<DBMovie> similarMovies;

	public RecommendedMovies()
	{
		super();
	}

	public RecommendedMovies(String likedMovieName, List<DBMovie> similarMovies)
	{
		super();
		this.likedMovieName = likedMovieName;
		this.similarMovies = similarMovies;
	}

	public String getLikedMovieName()
	{
		return likedMovieName;
	}

	public void setLikedMovieName(String likedMovieName)
	{
		this.likedMovieName = likedMovieName;
	}

	public List<DBMovie> getDbMovie()
	{
		return similarMovies;
	}

	public void setDbMovie(List<DBMovie> simMovies)
	{
		this.similarMovies = simMovies;
	}

	@Override
	public String toString()
	{
		return "RecommendedMovies [likedMovieName=" + likedMovieName + ", similarMovies=" + similarMovies + "]";
	}

}
