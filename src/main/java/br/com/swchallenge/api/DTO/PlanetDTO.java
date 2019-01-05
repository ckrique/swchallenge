package br.com.swchallenge.api.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PlanetDTO  extends BaseDTO{
	
	@JsonProperty("aparicoesEmFilmes") // TODO REMOVER ESSA ANOTAÇÃO PORQUE ESSE CAMPO VIRÁ DE PROCESSAMENTO
	private int amountOfTimesHasAppearedInMovies;
	@JsonProperty("climas")
	private List<ClimateDTO> climates;
	@JsonProperty("terrenos")
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
