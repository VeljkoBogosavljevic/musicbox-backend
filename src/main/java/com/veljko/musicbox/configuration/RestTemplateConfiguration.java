package com.veljko.musicbox.configuration;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public final class RestTemplateConfiguration {
	
	private static final int CONNECT_TIMEOUT = 10000;
	private static final int CONNECTION_REQUEST_TIMEOUT = 3000;
	private static final int READ_TIMEOUT = 2000;
	
	private RestTemplateConfiguration () {}
	
	public static ClientHttpRequestFactory configureHttpClientRequest () {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		clientHttpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);
		clientHttpRequestFactory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
		clientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);
		
		return clientHttpRequestFactory;
	}

}
