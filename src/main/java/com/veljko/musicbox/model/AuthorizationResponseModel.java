package com.veljko.musicbox.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class AuthorizationResponseModel implements Serializable {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8932434386969609815L;
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private int expiresIn;
	@JsonProperty("scope")
	private String scope;
	
	public AuthorizationResponseModel() {
		super();
	}

	@JsonGetter("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonSetter("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonGetter("token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonSetter("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@JsonGetter("expires_in")
	public int getExpiresIn() {
		return expiresIn;
	}

	@JsonSetter("expires_in")
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	@JsonGetter("scope")
	public String getScope() {
		return scope;
	}

	@JsonSetter("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}

}
