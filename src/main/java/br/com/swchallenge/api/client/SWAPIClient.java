package br.com.swchallenge.api.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.swchallenge.api.DTO.PlanetDTO;
import br.com.swchallenge.api.DTO.PlanetsRequisitionListDTO;

@Component
public class SWAPIClient {
	private static final String FIRST_PLANETS_PAGE_SWAPI_URL = "https://swapi.co/api/planets/?page=1";

	public List<PlanetDTO> getSWAPIPlanets() throws Exception {

		List<PlanetDTO> receivedPlanets = new ArrayList<PlanetDTO>();
		String nextPlanetUrl = "";

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ResponseEntity<PlanetsRequisitionListDTO> responseEntity;
		nextPlanetUrl = FIRST_PLANETS_PAGE_SWAPI_URL;

		do {
			responseEntity = restTemplate.getForEntity(nextPlanetUrl, PlanetsRequisitionListDTO.class);

			if (responseEntity.getStatusCode().equals(org.springframework.http.HttpStatus.OK)) {
				PlanetsRequisitionListDTO planetsRequisitionList = responseEntity.getBody();
				nextPlanetUrl = planetsRequisitionList.getNextPlanetListURL();

				if (planetsRequisitionList.getCount() > receivedPlanets.size()) {
					for (int i = 0; i < planetsRequisitionList.getResults().size(); i++) {
						PlanetDTO receivedPlanet = planetsRequisitionList.getResults().get(i);
						if (receivedPlanet.getName() != null && !receivedPlanet.getName().equals("")) {
							receivedPlanets.add(planetsRequisitionList.getResults().get(i));							
						}
					}
				}
			} else
				throw new Exception("Erro ao tentar consulta SWAPI."); // TODO MELHORAR MENSAGEM DA EXCEPTION
		} while (nextPlanetUrl != null && !nextPlanetUrl.equals(""));

		return receivedPlanets;
	}
}
