package com.veljko.musicbox.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Albums implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 7044054557558265321L;
	
	@JsonProperty("items")
	private List<Album> items;

	public Albums() {
		super();
	}

	@JsonGetter("items")
	public List<Album> getItems() {
		return items;
	}

	@JsonSetter("items")
	public void setItems(List<Album> items) {
		this.items = items;
	}
	
	

}
