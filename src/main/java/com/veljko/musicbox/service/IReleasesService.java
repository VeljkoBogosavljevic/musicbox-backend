package com.veljko.musicbox.service;

import com.veljko.musicbox.model.ReleasesResponseModel;

public interface IReleasesService {

	public ReleasesResponseModel fetchNewReleases (String market);
	
}
