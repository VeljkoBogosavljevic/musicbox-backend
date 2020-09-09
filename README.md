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
  - api_authorize_endpoint: "method=POST value = /authorize, headers = api_key, produces = application/json",
  - api_releases_endpoint: "method=GET value = /releases, params = market [required], produces = application/json",
  - api_album_endpoint: "method=GET value = /albums/{id}, params = market [optional], produces = application/json",
  - api_tracks_endpoint: "method=GET value = /albums/{id}/tracks, params = market [optional], produces = application/json"
