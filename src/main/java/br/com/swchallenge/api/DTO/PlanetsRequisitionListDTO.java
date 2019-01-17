package br.com.swchallenge.api.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetsRequisitionListDTO extends BaseDTO{	
	private int count;
	@JsonProperty("next")
	private String nextPlanetListURL;
	private List<PlanetDTO> results;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNextPlanetListURL() {
		return nextPlanetListURL;
	}

	public void setNextPlanetListURL(String nextPlanetListURL) {
		this.nextPlanetListURL = nextPlanetListURL;
	}

	public List<PlanetDTO> getResults() {
		return results;
	}

	public void setResults(List<PlanetDTO> results) {
		this.results = results;
	}	
	   
    public boolean hasNext() {
        return (nextPlanetListURL != null && !nextPlanetListURL.isEmpty());
    }
}
