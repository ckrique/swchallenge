package br.com.swchallenge.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;


@Document(collection="Planet")
@Data
@AllArgsConstructor
public class Planet extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "planets_sequence";
	
	@JsonProperty("numeroAparicoesEmFilmes")
	private int movieAppearances;	
	@JsonProperty("clima")
	private List<String> climate;
	@JsonProperty("terreno")
	private List<String> terrain;
	
	public Planet() {
		climate = new ArrayList<String>();
		terrain = new ArrayList<String>();
	}
	
	public int getMovieAppearances() {
		return movieAppearances;
	}

	public void setMovieAppearances(int movieAppearances) {
		this.movieAppearances = movieAppearances;
	}

	public List<String> getClimate() {
		return climate;
	}

	public void setClimate(List<String> climate) {
		this.climate = climate;
	}
	
	public void addClimate(String oneClimate) {
		this.climate.add(oneClimate);
	}

	public List<String> getTerrain() {
		return terrain;
	}

	public void setTerrain(List<String> terrain) {
		this.terrain = terrain;
	}
	
	public void addTerrain(String oneTerrain) {		
		this.terrain.add(oneTerrain);
	}
}
