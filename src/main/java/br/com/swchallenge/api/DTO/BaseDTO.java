package br.com.swchallenge.api.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDTO {	 
	private int id;
	@JsonProperty("nome")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
