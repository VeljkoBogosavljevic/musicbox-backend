package com.veljko.musicbox.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5468974109366130340L;
	
	@JsonProperty("height")
	private int height;
	
	@JsonProperty("width")
	private int width;
	
	@JsonProperty("url")
	private String url;
	
	public Image () {
		super();
	}

	@JsonGetter("height")
	public int getHeight() {
		return height;
	}

	@JsonGetter("height")
	public void setHeight(int height) {
		this.height = height;
	}

	@JsonSetter("width")
	public int getWidth() {
		return width;
	}

	@JsonSetter("width")
	public void setWidth(int width) {
		this.width = width;
	}

	@JsonGetter("url")
	public String getUrl() {
		return url;
	}

	@JsonSetter("url")
	public void setUrl(String url) {
		this.url = url;
	}

}
