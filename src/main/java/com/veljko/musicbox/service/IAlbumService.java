package com.veljko.musicbox.service;

import com.veljko.musicbox.model.Album;
import com.veljko.musicbox.model.TracksResponseModel;

public interface IAlbumService {

	public Album fetchAlbum (String id, String market);
	
	public TracksResponseModel fetchTracks (String id, String market);
	
}
