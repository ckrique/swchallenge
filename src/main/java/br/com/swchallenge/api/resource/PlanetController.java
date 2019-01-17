package br.com.swchallenge.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.exceptions.BadRequestException;
import br.com.swchallenge.api.exceptions.InternalFailureException;
import br.com.swchallenge.api.exceptions.SwChallengeException;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.service.PlanetService;

@RestController
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@GetMapping("/swchallenge/findByName/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Planet findByName(@PathVariable String name) {
		try {
			return planetService.findPlanetsByName(name);
		} catch (SwChallengeException swCex) {
			throw swCex;
		} catch (Exception ex) {
			throw new InternalFailureException();
		}
	}

	@GetMapping("/swchallenge/findByPlanetById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Planet findByPlanetById(@PathVariable int id) {
		try {
			return planetService.findPlanetsById(id);

		} catch (SwChallengeException swCex) {
			throw swCex;
		} catch (Exception ex) {
			throw new InternalFailureException();
		}
	}

	@GetMapping("/swchallenge/PlanetList")
	@ResponseStatus(HttpStatus.OK)
	public List<Planet> listPlanets() {
		try {
			return planetService.findPlanets();
		} catch (SwChallengeException swCex) {
			throw swCex;
		} catch (Exception ex) {
			throw new InternalFailureException();
		}
	}

	@DeleteMapping("/swchallenge/removePlanet/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePlanet(@PathVariable String name) {
		try {
			planetService.removePlanet(name);
		} catch (Exception ex) {
			throw new InternalFailureException();
		}		
	}

	@PostMapping("/swchallenge/addPlanet")
	@ResponseStatus(HttpStatus.CREATED)
	public PlanetDTO savePlanet(@RequestBody PlanetDTO planet) {
		try {
			return planetService.savePlanetByDTO(planet);
		} catch (SwChallengeException swCEx) {
			throw swCEx;
		} catch (Exception ex) {
			throw new BadRequestException();
		}
	}
}
