package com.veljko.musicbox.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleasesResponseModel implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6322592721795627997L;
	
	@JsonProperty("albums")
	private Albums albums;

	public ReleasesResponseModel() {
		super();
	}

	@JsonGetter("albums")
	public Albums getAlbums() {
		return albums;
	}

	@JsonSetter("albums")
	public void setAlbums(Albums albums) {
		this.albums = albums;
	}
	
	

}
