package com.veljko.musicbox.model.cache;

import java.time.LocalDateTime;
import java.util.Optional;

import com.veljko.musicbox.model.AuthorizationResponseModel;

public final class AuthorizationResponseModelCache {
	
	private static AuthorizationResponseModelCache instance;
	private AuthorizationResponseModel cachedAuthorizationResponseModel;
	private LocalDateTime updated;
	
	private AuthorizationResponseModelCache () {}
	
	public static AuthorizationResponseModelCache getInstance () {
		if (instance == null) {
			instance = new AuthorizationResponseModelCache();
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
