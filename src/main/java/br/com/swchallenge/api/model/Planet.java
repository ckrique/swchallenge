package br.com.swchallenge.api.model;

import java.util.ArrayList;
import java.util.List;

public class Planet extends BaseEntity{

	private int amountOfTimesHasAppearedInMovies;	
	private List<Climate> climates;
	private List<Terrain> terrains;
	
	public Planet() {
		climates = new ArrayList<Climate>();
		terrains = new ArrayList<Terrain>();
	}
	
	public int getAmountOfTimesHasAppearedInMovies() {
		return amountOfTimesHasAppearedInMovies;
	}

	public void setAmountOfTimesHasAppearedInMovies(int amountOfTimesHasAppearedInMovies) {
		this.amountOfTimesHasAppearedInMovies = amountOfTimesHasAppearedInMovies;
	}

	public List<Climate> getClimates() {
		return climates;
	}

	public void setClimates(List<Climate> climates) {
		this.climates = climates;
	}
	
	public void addClimate(Climate climate) {
		this.climates.add(climate);
	}

	public List<Terrain> getTerrain() {
		return terrains;
	}

	public void setTerrain(List<Terrain> terrains) {
		this.terrains = terrains;
	}
	
	public void addTerrain(Terrain terrain) {
		this.terrains.add(terrain);
	}
}
