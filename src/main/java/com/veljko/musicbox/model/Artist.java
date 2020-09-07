package com.veljko.musicbox.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3482118403596503103L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	public Artist () {
		super();
	}

	@JsonGetter("id")
	public String getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonGetter("name")
	public String getName() {
		return name;
	}

	@JsonSetter("name")
	public void setName(String name) {
		this.name = name;
	}
	
}
