package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EndUserJson {
	public static EndUserJson getEndUserData(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(filename), EndUserJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@JsonProperty("firstname")
	String firstname;
	@JsonProperty("lastname")  
	String lastname;
	@JsonProperty("email") 
	String email;
	@JsonProperty("address") 
	String address;
	@JsonProperty("company") 
	String company;
	@JsonProperty("password") 
	String password;
	
	public String getFirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getCompanyName() {
		return company;
	}

	public String getPassword() {
		return password;
	}
}
