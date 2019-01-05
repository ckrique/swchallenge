package br.com.swchallenge.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.swchallenge.api.model.Planet;

public interface PlanetRepositoty extends MongoRepository<Planet, Integer>{

}
