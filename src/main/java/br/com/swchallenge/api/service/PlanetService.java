package br.com.swchallenge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.swchallenge.api.DTO.ClimateDTO;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.DTO.TerrainDTO;
import br.com.swchallenge.api.model.BaseEntity;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.repository.PlanetRepositoty;

@Component
public class PlanetService extends BaseService {
	
	@Autowired
	private PlanetRepositoty planetRepositoty;
		
	public PlanetDTO savePlanet(PlanetDTO planetDTO) {
		Planet planet = extractEntityFromDTO(planetDTO);
		
		planetRepositoty.save(planet);	
		planetDTO.setId(planet.getId());
		
		return planetDTO;
	}
	
	
	public Planet extractEntityFromDTO(PlanetDTO planetDTO) {
		Planet planet = new Planet();
		ClimateService climateService = new ClimateService();
		TerrainService terrainService = new TerrainService();

		planet.setId(planetDTO.getId());
		planet.setAmountOfTimesHasAppearedInMovies(planetDTO.getAmountOfTimesHasAppearedInMovies());
		planet.setName(planetDTO.getName());

		for (ClimateDTO dto : planetDTO.getClimates()) {
			BaseEntity climate = new BaseEntity();			
			 climate =  climateService.extractEntityFromDTO(dto);			 
			 //TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA CLIMATE			 
			planet.addClimate(climate.getId(), climate.getName());
		}

		for (TerrainDTO dto : planetDTO.getTerrains()) {
			BaseEntity terrain = new BaseEntity();			
			terrain =  terrainService.extractEntityFromDTO(dto);	
			//TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA TERRAIN	
			planet.addTerrain(terrain.getId(), terrain.getName());
		}
		
		planetRepositoty.save(planet);
		
		return planet;
	}
	
	

}
