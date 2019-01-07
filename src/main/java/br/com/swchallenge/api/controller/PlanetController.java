package br.com.swchallenge.api.controller;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.service.PlanetService;

@RestController
public class PlanetController {
	
	@Autowired
	private PlanetService planetService;
	
	
	@PostMapping("/swchallenge/addPlanet")
	public String savePlanet(@RequestBody PlanetDTO planet) {
		
		try {
		planetService.savePlanet(planet);		
		}
		catch(ValidationException vEx) {
			vEx = vEx;
		}
		catch(Exception ex) {
			ex = ex;
		}
		return "Planeta adicionado com id: " + planet.getId();	
	}
}
