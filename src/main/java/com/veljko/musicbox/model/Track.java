package com.veljko.musicbox.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -5707459017834281471L;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("track_number")
	private int trackNumber;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("artists")
	private List<Artist> artists;
	
	public Track () {
		super();
	}

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonGetter("track_number")
	public int getTrackNumber() {
		return trackNumber;
	}

	@JsonSetter("track_number")
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	@JsonGetter("name")
	public String getName() {
		return name;
	}

	@JsonSetter("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter("artists")
	public List<Artist> getArtists() {
		return artists;
	}

	@JsonSetter("artists")
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

}
