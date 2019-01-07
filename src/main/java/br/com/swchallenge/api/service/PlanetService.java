package br.com.swchallenge.api.service;

import java.util.List;

import javax.validation.ValidationException;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.swchallenge.api.DTO.ClimateDTO;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.DTO.TerrainDTO;
import br.com.swchallenge.api.cliente.SWAPIClient;
import br.com.swchallenge.api.model.BaseEntity;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.repository.PlanetRepositoty;

@Component
public class PlanetService extends BaseService {

	@Autowired
	private PlanetRepositoty planetRepositoty;

	@Autowired
	private SWAPIClient swApiClient;

	public PlanetDTO savePlanet(PlanetDTO planetDTO) throws ValidationException, Exception {
		try {
			validatePlanet(planetDTO);

			Planet planet = extractEntityFromDTO(planetDTO);
			planetRepositoty.save(planet);
			// planetDTO.setId(planet.getId());

			if (planetDTO.getId() <= 0)
				throw new Exception("Error trying save Planet. The Planet could not be saved.");
		}
		catch (ValidationException vEx) {
			throw vEx;
		} 
		catch (Exception ex) {
			throw ex;
		}
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
			climate = climateService.extractEntityFromDTO(dto);
			// TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA CLIMATE
			planet.addClimate(climate.getId(), climate.getName());
		}

		for (TerrainDTO dto : planetDTO.getTerrains()) {
			BaseEntity terrain = new BaseEntity();
			terrain = terrainService.extractEntityFromDTO(dto);
			// TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA TERRAIN
			planet.addTerrain(terrain.getId(), terrain.getName());
		}

		planetRepositoty.save(planet);

		return planet;
	}

	private void validatePlanet(PlanetDTO planet) throws ValidationException, Exception {
		List<PlanetDTO> SWAPIPlanets = swApiClient.getSWAPIPlanets();

		if (planet.getId() > 0)
			throw new ValidationException("Ilavid data. Planet could not be saved.");

		if (!SWAPIPlanets.contains(planet))
			throw new ValidationException("The Planet name is not valid. Planet could not be saved.");
	}
}
