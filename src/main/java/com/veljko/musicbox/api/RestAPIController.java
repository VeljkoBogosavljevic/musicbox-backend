package com.veljko.musicbox.api;

import java.util.Optional;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veljko.musicbox.model.Album;
import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.model.ReleasesResponseModel;
import com.veljko.musicbox.model.TracksResponseModel;
import com.veljko.musicbox.service.IAlbumService;
import com.veljko.musicbox.service.IAuthorizationService;
import com.veljko.musicbox.service.IReleasesService;

@RestController
@RequestMapping("api/v1")
public class RestAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestAPIController.class);
	
	@Value("${rest.authorization.api_key}")
	private String authorizationApiKey;

	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("spotifyReleasesService")
	private IReleasesService releasesService;
	
	@Autowired
	@Qualifier("spotifyAlbumService")
	private IAlbumService albumService;
	
	@PostMapping(value = "/authorize", headers = "api_key", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorizationResponseModel> authorize (@RequestHeader(value = "api_key", required = true) String apiKey) {
		LOGGER.info("Authorize API endpoint");
		
		if (!isApiKeyValid(apiKey)) {
			LOGGER.warn("Api Key {} is not valid for the Authorize API endpoint", apiKey);
			return new ResponseEntity<AuthorizationResponseModel>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<AuthorizationResponseModel>(authorizationService.authorize(), HttpStatus.OK);	
	}
	
	@GetMapping(value = "/releases", params = "market", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReleasesResponseModel> releases (@RequestParam(value = "market", required = true) String market) {
		LOGGER.info("Releases API endpoint");
		
		if (!isMarketValid(market)) {
			LOGGER.warn("Market {} is not valid for the Releases API endpoint", market);
			return new ResponseEntity<ReleasesResponseModel>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ReleasesResponseModel>(releasesService.fetchNewReleases(market), HttpStatus.OK);	
	}
	
	@GetMapping(value = "/albums/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Album> album (@PathVariable(value = "id") String albumId, @RequestParam(value = "market", required = false) Optional<String> marketOpt) {
		LOGGER.info("Album API endpoint");
		
		if (!isAlbumIdValid(albumId)) {
			LOGGER.warn("Album ID {} is not valid for the Album API endpoint", albumId);
			return new ResponseEntity<Album>(HttpStatus.BAD_REQUEST);
		}
		
		if (marketOpt.isPresent() && !isMarketValid(marketOpt.get())) {
			LOGGER.warn("Market {} is not valid for the Album API endpoint", marketOpt.get());
			return new ResponseEntity<Album>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Album>(albumService.fetchAlbum(albumId, marketOpt), HttpStatus.OK);	
	}
	
	@GetMapping(value = "/albums/{id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TracksResponseModel> tracks (@PathVariable(value = "id") String albumId, @RequestParam(value = "market", required = false) Optional<String> marketOpt) {
		LOGGER.info("Tracks API endpoint");
		
		if (!isAlbumIdValid(albumId)) {
			LOGGER.warn("Album ID {} is not valid for the Tracks API endpoint", albumId);
			return new ResponseEntity<TracksResponseModel>(HttpStatus.BAD_REQUEST);
		}
		
		if (marketOpt.isPresent() && !isMarketValid(marketOpt.get())) {
			LOGGER.warn("Market {} is not valid for the Tracks API endpoint", marketOpt.get());
			return new ResponseEntity<TracksResponseModel>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<TracksResponseModel>(albumService.fetchTracks(albumId, marketOpt), HttpStatus.OK);	
	}
	
	private boolean isApiKeyValid (String apiKey) {
		if (apiKey == null || !apiKey.equals(authorizationApiKey)) {
			return false;
		}
		return true;
	}
	
	private boolean isAlbumIdValid (String id) {
		final Pattern albumIDPattern = Pattern.compile("^[a-zA-Z0-9]{22}$");	
		return albumIDPattern.matcher(id).matches();
	}
	
	private boolean isMarketValid (String market) {
		final Pattern iso3166_1Apha2Pattern = Pattern.compile("^(A(D|E|F|G|I|L|M|N|O|R|S|T|Q|U|W|X|Z)|B(A|B|D|E|F|G|H|I|J|L|M|N|O|R|S|T|V|W|Y|Z)|C(A|C|D|F|G|H|I|K|L|M|N|O|R|U|V|X|Y|Z)|D(E|J|K|M|O|Z)|E(C|E|G|H|R|S|T)|F(I|J|K|M|O|R)|G(A|B|D|E|F|G|H|I|L|M|N|P|Q|R|S|T|U|W|Y)|H(K|M|N|R|T|U)|I(D|E|Q|L|M|N|O|R|S|T)|J(E|M|O|P)|K(E|G|H|I|M|N|P|R|W|Y|Z)|L(A|B|C|I|K|R|S|T|U|V|Y)|M(A|C|D|E|F|G|H|K|L|M|N|O|Q|P|R|S|T|U|V|W|X|Y|Z)|N(A|C|E|F|G|I|L|O|P|R|U|Z)|OM|P(A|E|F|G|H|K|L|M|N|R|S|T|W|Y)|QA|R(E|O|S|U|W)|S(A|B|C|D|E|G|H|I|J|K|L|M|N|O|R|T|V|Y|Z)|T(C|D|F|G|H|J|K|L|M|N|O|R|T|V|W|Z)|U(A|G|M|S|Y|Z)|V(A|C|E|G|I|N|U)|W(F|S)|Y(E|T)|Z(A|M|W))$");
		
		return iso3166_1Apha2Pattern.matcher(market).matches();
	}
	
}
