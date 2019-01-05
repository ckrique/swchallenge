package br.com.swchallenge.api.DTO;

import java.util.List;

public class PlanetDTO  extends BaseDTO{
	
	private int amountOfTimesHasAppearedInMovies;	
	private List<ClimateDTO> climates;
	private List<TerrainDTO> terrains;

	public int getAmountOfTimesHasAppearedInMovies() {
		return amountOfTimesHasAppearedInMovies;
	}

	public void setAmountOfTimesHasAppearedInMovies(int amountOfTimesHasAppearedInMovies) {
		this.amountOfTimesHasAppearedInMovies = amountOfTimesHasAppearedInMovies;
	}

	public List<ClimateDTO> getClimates() {
		return climates;
	}

	public void setClimates(List<ClimateDTO> climates) {
		this.climates = climates;
	}

	public List<TerrainDTO> getTerrains() {
		return terrains;
	}

	public void setTerrains(List<TerrainDTO> terrains) {
		this.terrains = terrains;
	}

}
