package com.veljko.musicbox.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TracksResponseModel implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 7585902401372868046L;
	
	@JsonProperty("items")
	private List<Track> items;

	public TracksResponseModel() {
		super();
	}

	@JsonGetter("items")
	public List<Track> getItems() {
		return items;
	}

	@JsonSetter("items")
	public void setItems(List<Track> items) {
		this.items = items;
	}

}
