package com.codeit.rajat.movys.persistence.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author rajat
 * 
 *         Database entity ORM code here
 *
 */
@Entity
@Table(name = "liked_movie")
public class DBLikedMovie
{
	@Id
	@Column(name = "id")
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	private DBMovie movie;

	public DBLikedMovie()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public DBLikedMovie(long id, DBMovie movie)
	{
		super();
		this.id = id;
		this.movie = movie;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public DBMovie getMovie()
	{
		return movie;
	}

	public void setMovie(DBMovie movie)
	{
		this.movie = movie;
	}

	@Override
	public String toString()
	{
		return "DBLikedMovie [id=" + id + ", movie=" + movie + "]";
	}

}
