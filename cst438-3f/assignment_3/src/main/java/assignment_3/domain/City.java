package assignment_3.domain;

import javax.persistence.*;


@Entity
@Table(name="city")
public class City {
	
	@Id
	private long id;
	private String name;
	@Column(name="countrycode")
	private String countryCode;
	private String district;
	private int population;
	
	// Empty city object creation
	public City() {
		this(0, "name", "code", "district", 0);
	}
	
	// Create city object with arguments
	public City(long id, String name, String countryCode, String district, int population) {
		super();
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;

	}
	
	//------------Getters and Setters-------------//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}

	// Method to convert city object to string value
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryCode=" + countryCode + ", district=" + district
				+ ", population=" + population 
				+ "]";
	}
	
	

}

