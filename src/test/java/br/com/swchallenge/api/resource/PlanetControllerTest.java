package br.com.swchallenge.api.resource;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.swchallenge.api.AbstractControllerTest;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.service.PlanetService;

public class PlanetControllerTest extends AbstractControllerTest {
	@Autowired
	private PlanetService service;
	
	private PlanetDTO usedPlanetDTO = null;

	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		// clean up after each test method
		if(usedPlanetDTO != null) {
			service.removePlanet(usedPlanetDTO.getName());
		}
	}
	
	@Test
    public void testSavePlanet() throws Exception {

		String planetName = "Coruscant";
		String climateOne = "temperate";
		String climateTwo = "arid";
		String terrainOne = "desert";
		
		String uri = "http://localhost:8080/swchallenge/addPlanet";
		
        PlanetDTO planet = new PlanetDTO();
        planet.setName(planetName);
        planet.addClimateToList(climateOne);
        planet.addClimateToList(climateTwo);
        planet.addTerrainToList(terrainOne);
        
        String inputJson = super.mapToJson(planet);
        
        
        

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        usedPlanetDTO = super.mapFromJson(content, PlanetDTO.class);

        Assert.assertNotNull("failure - expected greeting not null",
        		usedPlanetDTO);
        Assert.assertNotNull("failure - expected greeting.id not null",
        		usedPlanetDTO.getId());
        Assert.assertEquals("failure - expected greeting.text match", planetName,
        		usedPlanetDTO.getName());
        Assert.assertTrue("failure - expected text attribute match", 
        		usedPlanetDTO.getClimatesList().contains(climateOne));
		Assert.assertTrue("failure - expected text attribute match",
				usedPlanetDTO.getClimatesList().contains(climateTwo));
		Assert.assertTrue("failure - expected text attribute match", 
				usedPlanetDTO.getTerrainsList().contains(terrainOne));

    }
	
	
	
}
