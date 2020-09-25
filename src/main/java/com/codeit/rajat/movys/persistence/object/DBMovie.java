package com.codeit.rajat.movys.persistence.object;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author rajat
 * 
 *         Database entity ORM code here
 */
@Entity
@Table(name = "movie")
public class DBMovie
{
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;
	private String originalTitle;
	private Double popularity;
	private boolean isAdult;
	@Column(length = 10000)
	private String overview;
	private Date releaseDate;
	private String posterPath;
	private String genres;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "liked_id", referencedColumnName = "id")
	private DBLikedMovie dbLikedMovie;

	public DBMovie()
	{
		super();
		this.popularity = 0.0;
	}

	public DBMovie(long id, String originalTitle, Double popularity, boolean isAdult, String overview, Date releaseDate,
			String posterPath, String genres)
	{
		super();
		this.id = id;
		this.originalTitle = originalTitle;
		this.popularity = popularity;
		this.isAdult = isAdult;
		this.overview = overview;
		this.releaseDate = releaseDate;
		this.posterPath = posterPath;
		this.genres = genres;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getOriginalTitle()
	{
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle)
	{
		this.originalTitle = originalTitle;
	}

	public Double getPopularity()
	{
		return popularity;
	}

	public void setPopularity(Double popularity)
	{
		this.popularity = popularity;
	}

	public boolean isAdult()
	{
		return isAdult;
	}

	public void setAdult(boolean isAdult)
	{
		this.isAdult = isAdult;
	}

	public String getOverview()
	{
		return overview;
	}

	public void setOverview(String overview)
	{
		this.overview = overview;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public String getPosterPath()
	{
		return posterPath;
	}

	public void setPosterPath(String posterPath)
	{
		this.posterPath = posterPath;
	}

	public String getGenres()
	{
		return genres;
	}

	public void setGenres(String genres)
	{
		this.genres = genres;
	}

	@Override
	public String toString()
	{
		return "Movie [id=" + id + ", originalTitle=" + originalTitle + ", popularity=" + popularity + ", isAdult="
				+ isAdult + ", overview=" + overview + ", releaseDate=" + releaseDate + ", posterPath=" + posterPath
				+ ", genres=" + genres + "]";
	}

}
