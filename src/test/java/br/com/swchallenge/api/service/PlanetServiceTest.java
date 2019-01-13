package br.com.swchallenge.api.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.swchallenge.api.AbstractTest;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.model.Planet;

@Transactional
public class PlanetServiceTest extends AbstractTest {
	@Autowired
	private PlanetService service;
	
	private Planet createdPlanet = null;

	@Before
	public void setUp() {
		// service.evictCache();
	}

	@After
	public void tearDown() {
		// clean up after each test method
		if(createdPlanet != null) {
			service.removePlanet(createdPlanet.getName());
		}
	}

	@Test
	public void testSave() {
		PlanetDTO planetDTO = new PlanetDTO();
		
		String planetName = "Coruscant";
		String climateOne = "temperate";
		String climateTwo = "arid";
		String terrainOne = "desert";
						
		planetDTO.setName(planetName);
		
		planetDTO.addClimateToList(climateOne);
		planetDTO.addClimateToList(climateTwo);		
		planetDTO.addTerrainToList(terrainOne);
		

		PlanetDTO createdDTO = service.savePlanetByDTO(planetDTO);
		createdPlanet = service.extractEntityFromDTO(createdDTO);

		Assert.assertNotNull("failure - expected not null", createdPlanet);
		Assert.assertNotNull("failure - expected id attribute not null", createdPlanet.getId());
		Assert.assertTrue("failure - expected id attribute more than zero", createdPlanet.getId() > 0);
		Assert.assertEquals("failure - expected text attribute match", planetName, createdPlanet.getName());
		Assert.assertTrue("failure - expected text attribute match", createdPlanet.getClimate().contains(climateOne));
		Assert.assertTrue("failure - expected text attribute match", createdPlanet.getClimate().contains(climateTwo));
		Assert.assertTrue("failure - expected text attribute match", createdPlanet.getTerrain().contains(terrainOne));

	}
}
