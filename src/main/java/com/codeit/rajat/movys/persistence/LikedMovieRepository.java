package com.codeit.rajat.movys.persistence;

import org.springframework.data.repository.CrudRepository;

import com.codeit.rajat.movys.persistence.object.DBLikedMovie;

public interface LikedMovieRepository extends CrudRepository<DBLikedMovie, Long>
{

}
