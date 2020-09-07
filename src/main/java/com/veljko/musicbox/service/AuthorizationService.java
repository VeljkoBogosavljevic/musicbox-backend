package com.veljko.musicbox.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.model.cache.AuthorizationResponseModelCache;

@Service("authorizationService")
public class AuthorizationService implements IAuthorizationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
	
	@Autowired
	@Qualifier("spotifyAuthorizationService")
	private IAuthorizationService spotifyAuthorizationService;

	@Override
	public AuthorizationResponseModel authorize () {
		Optional<AuthorizationResponseModel> cachedAuthorizationResponseModel = AuthorizationResponseModelCache.getInstance().getCachedAuthorizationResponseModel();
		
		if (cachedAuthorizationResponseModel.isPresent()) {
			LOGGER.info("Client authorized against cache");
			return cachedAuthorizationResponseModel.get();
		}
		
		return spotifyAuthorizationService.authorize();
	}

}
