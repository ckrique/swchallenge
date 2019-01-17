package br.com.swchallenge.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import br.com.swchallenge.api.exceptions.SwChallengeException;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.repository.PlanetRepositoty;

@Component
public class PlanetService extends BaseService {

	@Autowired
	private PlanetRepositoty planetRepositoty;

	@Autowired
	private SWAPIClient swApiClient;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static List<PlanetDTO> SWAPIPlanets = null;
	private static LocalDateTime lastAccesDateTime = null;

	public void delete(Planet planet) {
		planetRepositoty.delete(planet);
	}

	public List<Planet> findPlanets() {		
		return planetRepositoty.findAll();
	}

	public Planet findPlanetsByName(String name) {		
		return planetRepositoty.findByName(name);
	}

	public Planet findPlanetsById(int id) {
		Optional<Planet> encapsulatedPlanet = null;

		encapsulatedPlanet = planetRepositoty.findById(id);
		if(encapsulatedPlanet.isPresent())
			return encapsulatedPlanet.get();
		else
			return new Planet();
	}

	public void removePlanet(String name) {

		Planet planet = new Planet();

		planet = findPlanetsByName(name);

		if (planet != null && planet.getName() != null && !planet.getName().equals("")) {
			delete(planet);
			logger.info("< removed planet name:{} and id:{}", planet.getName(), planet.getId());
		}
		else
			logger.info("- Any Planet was found to be removed");
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

		if (SWAPIPlanets == null && lastAccesDateTime == null) {
			SWAPIPlanets = swApiClient.getSWAPIPlanets();
		} else if (SWAPIPlanets != null && lastAccesDateTime != null
				&& ChronoUnit.HOURS.between(LocalDateTime.now(), lastAccesDateTime) > 5) {
			SWAPIPlanets = swApiClient.getSWAPIPlanets();
		}

		lastAccesDateTime = LocalDateTime.now();
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
