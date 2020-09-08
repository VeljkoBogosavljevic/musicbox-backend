# musicbox-backend application
MusicBox backend services for Spotify integration

### Spring Boot 2.3.3 application
Run with: 
```
./mvnw spring-boot:run
or
mvn spring-boot:run
```
### App info
> http://localhost:8080/actuator/info

**Endpoints**

- api_base_path: "api/v1",
- api_authorize_endpoint: "value = /authorize, headers = api_key, produces = application/json",
- api_releases_endpoint: "value = /releases, params = market [required], produces = application/json",
- api_album_endpoint: "value = /albums/{id}, params = market [optional], produces = application/json",
- api_tracks_endpoint: "value = /albums/{id}/tracks, params = market [optional], produces = application/json"
