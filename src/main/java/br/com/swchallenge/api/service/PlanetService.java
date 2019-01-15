package br.com.swchallenge.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.client.SWAPIClient;
import br.com.swchallenge.api.exceptions.AlreadyRecordedDataException;
import br.com.swchallenge.api.exceptions.BadRequestException;
import br.com.swchallenge.api.exceptions.NotIsASWPlanetException;
import br.com.swchallenge.api.exceptions.PlanetNotFoudException;
import br.com.swchallenge.api.exceptions.PlanetsNotFoudException;
import br.com.swchallenge.api.exceptions.SwChallengeException;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.repository.PlanetRepositoty;

@Component
public class PlanetService extends BaseService {

	@Autowired
	private PlanetRepositoty planetRepositoty;

	@Autowired
	private SWAPIClient swApiClient;

	private static List<PlanetDTO> SWAPIPlanets;

	public void delete(Planet planet) {
		planetRepositoty.delete(planet);
	}
	
	public List<Planet> findPlanets() throws ValidationException {
		List<Planet> planets = new ArrayList<Planet>();

		planets = planetRepositoty.findAll();

		if (planets.size() <= 0)
			throw new PlanetsNotFoudException();

		return planets;
	}	

	public Planet findPlanetsByName(String name) throws PlanetNotFoudException {
		Planet planet = null;

		planet = planetRepositoty.findByName(name);

		if (planet == null)
			throw new PlanetNotFoudException();

		return planet;
	}

	public Planet findPlanetsById(int id) throws PlanetsNotFoudException {
		Optional<Planet> encapsulatedPlanet = null;

		encapsulatedPlanet = planetRepositoty.findById(id);

		if (!encapsulatedPlanet.isPresent())
			throw new PlanetNotFoudException();

		return encapsulatedPlanet.get();
	}

	public void removePlanet(String name) throws PlanetNotFoudException {

		Planet planet = null;

		planet = findPlanetsByName(name);

		if (planet != null)
			delete(planet);
		else
			throw new PlanetNotFoudException();
	}
	
	public void save(Planet planet) {
		planetRepositoty.save(planet);
	}
	
	public PlanetDTO savePlanetByDTO(PlanetDTO planetDTO)
			throws NotIsASWPlanetException, AlreadyRecordedDataException, BadRequestException {
		try {
			callSWAPIToGetPlanets();

			validatePlanet(planetDTO);
			
			planetDTO.setMovieAppearances(getMovieAppearances(planetDTO));			

			Planet planet = extractEntityFromDTO(planetDTO);
			save(planet);
			planetDTO.setId(planet.getId());

		} catch (SwChallengeException swCEx) {
			throw swCEx;
		} catch (Exception ex) {
			throw new BadRequestException();
		}
		return planetDTO;
	}
			
	public Planet extractEntityFromDTO(PlanetDTO planetDTO) {// COLOCAR VALIDAÇÕES CONTRA NULL POINTER
		Planet planet = new Planet();
		
		planet.setId(planetDTO.getId());
		planet.setMovieAppearances(planetDTO.getMovieAppearances());
		planet.setName(planetDTO.getName());

		for (String oneClimate : planetDTO.getClimatesList())		
			planet.addClimate(oneClimate);		

		for (String oneTerrain : planetDTO.getTerrainsList())		
			planet.addTerrain(oneTerrain);

		return planet;
	}

	private void callSWAPIToGetPlanets() throws Exception {
		SWAPIPlanets = swApiClient.getSWAPIPlanets();
	}

	private int getMovieAppearances(PlanetDTO planet) {
		HashMap<String, PlanetDTO> planetMap = new HashMap<String, PlanetDTO>();

		if (SWAPIPlanets != null)
			for (PlanetDTO SWAPIPlanet : SWAPIPlanets)
				planetMap.put(SWAPIPlanet.getName(), SWAPIPlanet);

		PlanetDTO pdto = planetMap.get(planet.getName());
		int count = pdto.getMovieAppearances();
		return count;
	}

	private void validatePlanet(PlanetDTO planet) throws ValidationException, Exception {

		if (planet.getId() > 0 || planet.getName().equals(""))
			throw new BadRequestException();

		if (planetRepositoty.findByName(planet.getName()) != null)
			throw new AlreadyRecordedDataException();

		if (!SWAPIPlanets.contains(planet))
			throw new NotIsASWPlanetException();
	}
}
