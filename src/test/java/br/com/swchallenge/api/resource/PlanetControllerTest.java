package br.com.swchallenge.api.resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.swchallenge.api.AbstractControllerTest;
import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.model.Planet;
import br.com.swchallenge.api.service.PlanetService;

public class PlanetControllerTest extends AbstractControllerTest {
	@Autowired
	private PlanetService service;

	private PlanetDTO usedPlanetDTO = null;
	private Planet usedPlanet = null;

	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		// clean up after each test method
		if (usedPlanetDTO != null) {
			service.removePlanet(usedPlanetDTO.getName());
		}
	}

	@Test
	public void testfindByPlanetById() throws Exception {

		int status;
		String content;
		String inputJson;
		String uriFind = "/swchallenge/findByPlanetById/{id}";
		String uriSave = "/swchallenge/addPlanet";

		String planetName = "Ryloth";
		String climateOne = "temperate";
		String climateTwo = "arid";
		String terrainOne = "desert";

		PlanetDTO planet = new PlanetDTO();
		planet.setName(planetName);
		planet.addClimateToList(climateOne);
		planet.addClimateToList(climateTwo);
		planet.addTerrainToList(terrainOne);

		inputJson = super.mapToJson(planet);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uriSave).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		content = result.getResponse().getContentAsString();

		usedPlanetDTO = super.mapFromJson(content, PlanetDTO.class);

		result = mvc
				.perform(MockMvcRequestBuilders.get(uriFind, usedPlanetDTO.getId()).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		content = result.getResponse().getContentAsString();
		status = result.getResponse().getStatus();
		
		usedPlanet = super.mapFromJson(content, Planet.class);
		

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		Assert.assertEquals("failure - expected Planet name text match", planetName, usedPlanet.getName());
		Assert.assertTrue("failure - expected text attribute match", usedPlanet.getClimate().contains(climateOne));
		Assert.assertTrue("failure - expected text attribute match", usedPlanet.getClimate().contains(climateTwo));
		Assert.assertTrue("failure - expected text attribute match", usedPlanet.getTerrain().contains(terrainOne));

	}

	@Test
	public void testSavePlanet() throws Exception {

		String planetName = "Coruscant";
		String climateOne = "temperate";
		String climateTwo = "arid";
		String terrainOne = "desert";

		String uri = "/swchallenge/addPlanet";

		PlanetDTO planet = new PlanetDTO();
		planet.setName(planetName);
		planet.addClimateToList(climateOne);
		planet.addClimateToList(climateTwo);
		planet.addTerrainToList(terrainOne);

		String inputJson = super.mapToJson(planet);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

		usedPlanetDTO = super.mapFromJson(content, PlanetDTO.class);

		Assert.assertNotNull("failure - expected Planet not null", usedPlanetDTO);
		Assert.assertNotNull("failure - expected Planet.id not null", usedPlanetDTO.getId());
		Assert.assertEquals("failure - expected Planet.name match", planetName, usedPlanetDTO.getName());
		Assert.assertTrue("failure - expected Planet text attribute match", usedPlanetDTO.getClimatesList().contains(climateOne));
		Assert.assertTrue("failure - expected Planet text attribute match", usedPlanetDTO.getClimatesList().contains(climateTwo));
		Assert.assertTrue("failure - expected Planet text attribute match", usedPlanetDTO.getTerrainsList().contains(terrainOne));

	}

}
