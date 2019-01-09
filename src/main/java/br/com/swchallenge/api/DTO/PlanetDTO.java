package br.com.swchallenge.api.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetDTO extends BaseDTO {

	
	private int amountOfTimesHasAppearedInMovies;
	private String climate;
	private String terrain;
	private List<ClimateDTO> climatesList;
	private List<TerrainDTO> terrainsList;

	
	public int getAmountOfTimesHasAppearedInMovies() {
		return amountOfTimesHasAppearedInMovies;
	}

	public void setAmountOfTimesHasAppearedInMovies(int amountOfTimesHasAppearedInMovies) {
		this.amountOfTimesHasAppearedInMovies = amountOfTimesHasAppearedInMovies;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		List<String> climateNames = new ArrayList<String>();
		this.climate = climate;		
		climatesList = new ArrayList<ClimateDTO>();		
								
		if(!climate.trim().equals(""))
			climateNames = Arrays.asList(climate.split(","));
		
		for(String climateName : climateNames) {
			if(climateName.startsWith(" "))
				climateName = climateName.replaceAll("^\\s+","");
			
			climatesList.add(new ClimateDTO(climateName));
		}
		
		setClimatesList(climatesList);
	}

	public List<ClimateDTO> getClimatesList() {
		return climatesList;
	}

	public void setClimatesList(List<ClimateDTO> climates) {
		this.climatesList = climates;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		List<String> terrainNames = new ArrayList<String>();
		this.terrain = terrain;		
		terrainsList = new ArrayList<TerrainDTO>();	
		
		if(!terrain.trim().equals(""))
			terrainNames = Arrays.asList(terrain.split(","));
		
		for(String terrainName : terrainNames) {
			if(terrainName.startsWith(" "))
				terrainName = terrainName.replaceAll("^\\s+","");
			
			terrainsList.add(new TerrainDTO(terrainName));
		}
		
		setTerrainsList(terrainsList);		
	}

	public List<TerrainDTO> getTerrainsList() {
		return terrainsList;
	}

	public void setTerrainsList(List<TerrainDTO> terrains) {
		this.terrainsList = terrains;
	}

}
