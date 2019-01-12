package br.com.swchallenge.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
	
	private static List<PlanetDTO> SWAPIPlanets;
	
	public List<Planet> findPlanets() throws ValidationException{
		List<Planet> planets = new ArrayList<Planet>();
		
		planets  = planetRepositoty.findAll();
			
		if(planets.size() <=0 )
			throw new ValidationException("There is no Planets on database.");
		
		return planets;
	}
	
	public void removePlanet(String name)  throws ValidationException{{
		Planet planet = null;
		
		planet = findPlanetsByName(name);
		
		if(planet != null)
			planetRepositoty.delete(planet);
		else
			throw new ValidationException("Planet was not found.");
		}		
   	}
	
	public Planet findPlanetsByName(String name) throws ValidationException{
		Planet planet = null;
		
		planet  = planetRepositoty.findByName(name);
			
		if(planet == null )
			throw new ValidationException("Planet was not found.");
		
		return planet;
	}
	
	public Optional<Planet> findPlanetsById(int id) throws ValidationException{
		Optional<Planet> planet = null;
		
		planet  = planetRepositoty.findById(id);
							
		if(planet == null )
			throw new ValidationException("Planet was not found.");
		
		return planet;
	}
	
	public PlanetDTO savePlanet(PlanetDTO planetDTO) throws ValidationException, Exception {
		try {
			callSWAPIToGetPlanets();
			
			planetDTO.setMovieAppearances(getMovieAppearances(planetDTO));
			
			validatePlanet(planetDTO);
			
			Planet planet = extractEntityFromDTO(planetDTO);
			planetRepositoty.save(planet);
			// planetDTO.setId(planet.getId());

			//if (planetDTO.getId() <= 0)
				//throw new Exception("Error trying save Planet. The Planet could not be saved.");
		}		
		catch (ValidationException vEx) {
			throw vEx;
		} 
		catch (Exception ex) {
			throw new Exception("Error while trying of add Planet.");
		}
		return planetDTO;
	}

	public Planet extractEntityFromDTO(PlanetDTO planetDTO) {//COLOCAR VALIDAÇÕES CONTRA NULL POINTER
		Planet planet = new Planet();
		ClimateService climateService = new ClimateService();
		TerrainService terrainService = new TerrainService();

		planet.setId(planetDTO.getId());
		planet.setMovieAppearances(planetDTO.getMovieAppearances());
		planet.setName(planetDTO.getName());

		for (ClimateDTO dto : planetDTO.getClimatesList()) {
			BaseEntity climate = new BaseEntity();
			climate = climateService.extractEntityFromDTO(dto);
			// TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA CLIMATE
			planet.addClimate(climate.getId(), climate.getName());
		}

		for (TerrainDTO dto : planetDTO.getTerrainsList()) {
			BaseEntity terrain = new BaseEntity();
			terrain = terrainService.extractEntityFromDTO(dto);
			// TODO: TENTAR MELHORAR ESSE CAST DE BASEENTITY PARA TERRAIN
			planet.addTerrain(terrain.getId(), terrain.getName());
		}

		return planet;
	}
	
	private void callSWAPIToGetPlanets() throws Exception {
		SWAPIPlanets = swApiClient.getSWAPIPlanets();
	}

	private int getMovieAppearances(PlanetDTO planet) {		
		HashMap<String, PlanetDTO> planetMap = new HashMap<String, PlanetDTO>();
		
		if(SWAPIPlanets != null)
			for(PlanetDTO SWAPIPlanet : SWAPIPlanets)
				planetMap.put(SWAPIPlanet.getName(), SWAPIPlanet);
		
		PlanetDTO pdto =  planetMap.get(planet.getName());
		int count = pdto.getMovieAppearances();
		return count;
	}
	
	private void validatePlanet(PlanetDTO planet) throws ValidationException, Exception {
		//List<PlanetDTO> SWAPIPlanets = swApiClient.getSWAPIPlanets();
		
		if (planet.getId() > 0)
			throw new ValidationException("Ilavid data. Planet could not be saved.");

		if (!SWAPIPlanets.contains(planet))
			throw new ValidationException("The Planet name is not valid. Planet could not be saved.");
	}
}
