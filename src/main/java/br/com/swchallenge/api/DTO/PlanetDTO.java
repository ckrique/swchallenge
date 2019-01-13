package br.com.swchallenge.api.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetDTO extends BaseDTO {

	private int movieAppearances;
	private String climate;
	private String terrain;
	@JsonProperty("clima")
	private List<String> climatesList;
	@JsonProperty("films")
	private List<String> filmsUrls;
	@JsonProperty("terreno")
	private List<String> terrainsList;

	public String getClimate() {
		return climate;
	}

	// Fill climatesList by climate string with one or more climates separated by
	// comma
	public void setClimate(String climate) {
		List<String> climateNames = new ArrayList<String>();
		this.climate = climate;

		climatesList = new ArrayList<String>();

		if (!climate.trim().equals(""))
			climateNames = Arrays.asList(climate.split(","));

		for (String climateName : climateNames) {
			if (climateName.startsWith(" "))
				climateName = climateName.replaceAll("^\\s+", "");

			addClimateToList(climateName);
		}
	}

	public void addClimateToList(String climateName) {
		climatesList.add(climateName);
	}

	public List<String> getClimatesList() {
		return climatesList;
	}

	public void setClimatesList(List<String> climates) {
		this.climatesList = climates;
	}

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(List<String> filmsUrls) {
		this.filmsUrls = filmsUrls;

		for (String film : filmsUrls) {
			if (film != null && !film.equals(""))
				setMovieAppearances(getMovieAppearances() + 1);
		}
	}

	public int getMovieAppearances() {
		return movieAppearances;
	}

	public void setMovieAppearances(int movieAppearances) {
		this.movieAppearances = movieAppearances;
	}

	public String getTerrain() {
		return terrain;
	}

	// Fill terrainList by terrain string with one or more terrains separated by
	// comma
	public void setTerrain(String terrain) {
		List<String> terrainNames = new ArrayList<String>();
		this.terrain = terrain;

		terrainsList = new ArrayList<String>();

		if (!climate.trim().equals(""))
			terrainNames = Arrays.asList(climate.split(","));

		for (String terraineName : terrainNames) {
			if (terraineName.startsWith(" "))
				terraineName = terraineName.replaceAll("^\\s+", "");

			addClimateToList(terraineName);
		}
	}

	public void addTerrainToList(String terrainName) {
		climatesList.add(terrainName);
	}

	public List<String> getTerrainsList() {
		return terrainsList;
	}

	public void setTerrainsList(List<String> terrains) {
		this.terrainsList = terrains;
	}

}
