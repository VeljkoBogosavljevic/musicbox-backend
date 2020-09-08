package com.veljko.musicbox.service;

import java.util.Optional;

import com.veljko.musicbox.model.Album;
import com.veljko.musicbox.model.TracksResponseModel;

public interface IAlbumService {

	public Album fetchAlbum (String id, Optional<String> marketOpt);
	
	public TracksResponseModel fetchTracks (String id, Optional<String> marketOpt);
	
}
