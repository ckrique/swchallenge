package br.com.swchallenge.api.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private List<PlanetDTO> usedPlanetDTOs = null;
	private PlanetDTO usedPlanetDTO = null;
	private Planet usedPlanet = null;

	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		// clean up after each test method
		

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

		try {
			inputJson = super.mapToJson(planet);

			MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uriSave).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

			content = result.getResponse().getContentAsString();

			usedPlanetDTO = super.mapFromJson(content, PlanetDTO.class);

			result = mvc.perform(
					MockMvcRequestBuilders.get(uriFind, usedPlanetDTO.getId()).accept(MediaType.APPLICATION_JSON))
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
		} catch (Exception ex) {
			throw ex;
		} finally {
			service.removePlanet(planet.getName());
			usedPlanet = null;
		}
	}

	@Test
	public void testfindByPlanetName() throws Exception {

		int status;
		String content;
		String inputJson;
		String uriFind = "/swchallenge/findByName/{name}";
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

		try {
			inputJson = super.mapToJson(planet);

			MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uriSave).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

			content = result.getResponse().getContentAsString();

			usedPlanetDTO = super.mapFromJson(content, PlanetDTO.class);

			result = mvc.perform(
					MockMvcRequestBuilders.get(uriFind, usedPlanetDTO.getName()).accept(MediaType.APPLICATION_JSON))
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
		} catch (Exception ex) {
			throw ex;
		} finally {
			service.removePlanet(planet.getName());
			usedPlanet = null;
		}
	}

	@Test
	public void testListPlanets() throws Exception {

		int status;
		String content;
		String inputJson;
		String uriFind = "/swchallenge/PlanetList";
		String uriSave = "/swchallenge/addPlanet";

		String planetOneName = "Ryloth";
		String planetTwoName = "Sullust";
		String climateOne = "temperate";
		String climateTwo = "arid";
		String terrainOne = "desert";
		String terrainTwo = "Rocks";

		List<Planet> receivedPlanets = new ArrayList<Planet>();
		usedPlanetDTOs = new ArrayList<PlanetDTO>();
		PlanetDTO planet = new PlanetDTO();

		// First Planet
		planet.setName(planetOneName);
		planet.addClimateToList(climateOne);
		planet.addTerrainToList(terrainOne);

		usedPlanetDTOs.add(planet);

		// Secound Planet
		planet = new PlanetDTO();

		planet.setName(planetTwoName);
		planet.addClimateToList(climateTwo);
		planet.addTerrainToList(terrainTwo);

		usedPlanetDTOs.add(planet);

		try {

			// First Add
			inputJson = super.mapToJson(usedPlanetDTOs.get(0));
			MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uriSave).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

			// Secound Add
			inputJson = super.mapToJson(usedPlanetDTOs.get(1));
			result = mvc.perform(MockMvcRequestBuilders.post(uriSave).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

			// find All
			result = mvc.perform(MockMvcRequestBuilders.get(uriFind).accept(MediaType.APPLICATION_JSON)).andReturn();

			content = result.getResponse().getContentAsString();
			status = result.getResponse().getStatus();

			receivedPlanets = Arrays.asList(super.mapFromJson(content, Planet[].class));

			Assert.assertEquals("failure - expected HTTP status 200", 200, status);
			Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
			Assert.assertTrue("failure - expected two Planets", receivedPlanets.size() >= 2);

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (usedPlanetDTOs != null) {
				for (PlanetDTO onePlanet : usedPlanetDTOs) {
					service.removePlanet(onePlanet.getName());
				}
			}

			usedPlanetDTOs = null;
		}

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

		try {

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
			Assert.assertTrue("failure - expected Planet text attribute match",
					usedPlanetDTO.getClimatesList().contains(climateOne));
			Assert.assertTrue("failure - expected Planet text attribute match",
					usedPlanetDTO.getClimatesList().contains(climateTwo));
			Assert.assertTrue("failure - expected Planet text attribute match",
					usedPlanetDTO.getTerrainsList().contains(terrainOne));
		} catch (Exception ex) {
			throw ex;
		} finally {
			service.removePlanet(planet.getName());
			usedPlanet = null;
		}
	}

}
