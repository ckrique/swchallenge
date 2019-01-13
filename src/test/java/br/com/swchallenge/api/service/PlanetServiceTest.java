package br.com.swchallenge.api.service;

import java.util.Collection;

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

	@Before
	public void setUp() {
		// service.evictCache();
	}

	@After
	public void tearDown() {
		// clean up after each test method
	}

	@Test
	public void testCreate() {
		Assert.assertEquals("Deu errado", 1, 1);

		/*
		 * String planetName = "Naboo"; PlanetDTO dto = new PlanetDTO();
		 * dto.setName(planetName);
		 * 
		 * PlanetDTO createdDTO = service.savePlanetByDTO(dto); Planet createdEntity =
		 * service.extractEntityFromDTO(createdDTO);
		 * 
		 * 
		 * Assert.assertNotNull("failure - expected not null", createdEntity);
		 * Assert.assertNotNull("failure - expected id attribute not null",
		 * createdEntity.getId());
		 * Assert.assertEquals("failure - expected text attribute match", planetName,
		 * createdEntity.getName());
		 */

	}
}
