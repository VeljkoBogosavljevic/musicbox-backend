package com.veljko.musicbox.model;

import java.time.LocalDateTime;
import java.util.Optional;

public final class AuthorizationResponseModelCacheHolder {
	
	private static AuthorizationResponseModelCacheHolder instance;
	private AuthorizationResponseModel cachedAuthorizationResponseModel;
	private LocalDateTime updated;
	
	private AuthorizationResponseModelCacheHolder () {}
	
	public static AuthorizationResponseModelCacheHolder getInstance () {
		if (instance == null) {
			instance = new AuthorizationResponseModelCacheHolder();
		}
		return instance;
	}
	
	public void updateAuthorizationResponseModel (AuthorizationResponseModel cachedAuthorizationResponseModel, LocalDateTime updated) {
		this.cachedAuthorizationResponseModel = cachedAuthorizationResponseModel;
		this.updated = updated;
	}
	
	public Optional<AuthorizationResponseModel> getCachedAuthorizationResponseModel () {
		if (this.cachedAuthorizationResponseModel != null && updated.plusSeconds(this.cachedAuthorizationResponseModel.getExpiresIn()).isBefore(LocalDateTime.now())) {
			this.cachedAuthorizationResponseModel = null;
		}
		return Optional.ofNullable(this.cachedAuthorizationResponseModel);
	}

}
