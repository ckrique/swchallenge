package br.com.swchallenge.api.DTO;


import com.fasterxml.jackson.annotation.JsonSetter;

public class BaseDTO {
	private int id;	
	private String name;

	public BaseDTO() {
		
	}
	
	public BaseDTO(String name) {
		super();
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	//Used to fill DTO from swchallenge requisition
	@JsonSetter("name")
	public void setName(String name) {
		this.name = name;
	}
	
	//Used to fill DTO from swapi requisition
	@JsonSetter("nome")
	public void setNome(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toUpperCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDTO other = (BaseDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}
}
