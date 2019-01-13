package br.com.swchallenge.api.resource;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.swchallenge.api.AbstractControllerTest;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.service.PlanetService;

public class PlanetControllerTest extends AbstractControllerTest {
	@Autowired
	private PlanetService service;
	
	private Planet createdPlanet = null;

	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		// clean up after each test method
		if(createdPlanet != null) {
			service.removePlanet(createdPlanet.getName());
		}
	}
	
	
	
	
}
