package com.codeit.rajat.movys.persistence;

import org.springframework.data.repository.CrudRepository;

import com.codeit.rajat.movys.persistence.object.DBMovie;

public interface MovieRepository extends CrudRepository<DBMovie, Long>
{

}
