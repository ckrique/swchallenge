package br.com.swchallenge.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.service.PlanetService;

@RestController
public class PlanetController {
	
	@Autowired
	private PlanetService planetService;
	
	
	
	@DeleteMapping("/swchallenge/removePlanet/{name}")
	public String removePlanet(@PathVariable String name) {		
		try {			
			planetService.removePlanet(name);			
			return "Planet removed.";			
		}
		catch(ValidationException vEx) {
			return vEx.getMessage();
		}	
		catch(Exception ex) {
			return "Error while removing Planet.";
		}		
	}
	
	
	@GetMapping("/swchallenge/findByName/{name}")
	public Planet findByName(@PathVariable String name) {
		
		try {
			return planetService.findPlanetsByName(name);
		}
		catch(Exception ex) {
			return null;
		}		
	}
	
	@GetMapping("/swchallenge/findByPlanetById/{id}")
	public Optional<Planet> findByPlanetById(@PathVariable int id) {
		
		try {
			return planetService.findPlanetsById(id);
		}
		catch(Exception ex) {
			return null;
		}		
	}
	
	
	@GetMapping("/swchallenge/list")
	public List<Planet> listPlanets() {//TODO: USAR OU NÃO DTO:?????????
		
		try {
			return planetService.findPlanets();
		}
		catch(Exception ex) {
			return null;
		}		
	}
	
	@PostMapping("/swchallenge/addPlanet")
	public String savePlanet(@RequestBody PlanetDTO planet) {		
		try {
		planetService.savePlanet(planet);		
		}
		catch(ValidationException vEx) {
			return vEx.getMessage();
		}
		catch(Exception ex) {
			return ex.getMessage();
		}
		//TODO: IMPLEMENTAR EXCEPTION PARA RETORNAR ERRO E O STATUS DE ERRO
		return "Planeta adicionado com id: " + planet.getId();	
	}
}
