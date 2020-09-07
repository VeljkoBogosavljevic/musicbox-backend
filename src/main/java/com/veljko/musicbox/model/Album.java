package com.veljko.musicbox.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5557015181525036744L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("album_type")
	private String albumType;
	
	@JsonProperty("total_tracks")
	private int totalTracks;
	
	@JsonProperty("images")
	private List<Image> images;
	
	@JsonProperty("artists")
	private List<Artist> artists;

	public Album() {
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

	@JsonGetter("album_type")
	public String getAlbumType() {
		return albumType;
	}

	@JsonSetter("album_type")
	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}
	
	@JsonGetter("total_tracks")
	public int getTotalTracks() {
		return totalTracks;
	}

	@JsonSetter("total_tracks")
	public void setTotalTracks(int totalTracks) {
		this.totalTracks = totalTracks;
	}

	@JsonGetter("images")
	public List<Image> getImages() {
		return images;
	}

	@JsonSetter("images")
	public void setImages(List<Image> images) {
		this.images = images;
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
