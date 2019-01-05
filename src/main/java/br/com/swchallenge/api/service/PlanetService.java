package br.com.swchallenge.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.swchallenge.api.DTO.ClimateDTO;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.DTO.TerrainDTO;
import br.com.swchallenge.api.model.Climate;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.model.Terrain;
import br.com.swchallenge.api.repository.PlanetRepositoty;

public class PlanetService extends BaseService {
	
	@Autowired
	private PlanetRepositoty planetRepositoty;
	
	
	public void SavePlanet(PlanetDTO planetDTO) {

	}

	public void savePlanet(PlanetDTO planetDTO) {
		Planet planet = extractEntityFromDTO(planetDTO);
		
		planetRepositoty.save(planet);		
	}
	
	
	public Planet extractEntityFromDTO(PlanetDTO planetDTO) {
		Planet planet = new Planet();
		ClimateService climateService = new ClimateService();
		TerrainService terrainService = new TerrainService();

		planet.setId(planetDTO.getId());
		planet.setAmountOfTimesHasAppearedInMovies(planetDTO.getAmountOfTimesHasAppearedInMovies());
		planet.setName(planetDTO.getName());

		for (ClimateDTO dto : planetDTO.getClimates()) {
			planet.addClimate((Climate) climateService.extractEntityFromDTO(dto));
		}

		for (TerrainDTO dto : planetDTO.getTerrains()) {
			planet.addTerrain((Terrain) terrainService.extractEntityFromDTO(dto));
		}

		return new Planet();
	}
	
	

}
