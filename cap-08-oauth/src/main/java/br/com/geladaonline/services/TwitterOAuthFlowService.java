package br.com.geladaonline.services;

import javax.servlet.http.HttpServletRequest;

import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

public class TwitterOAuthFlowService {
	
	private static final String CONSUMER_KEY = "C0udNvSiUP94pNF3Xr72dFRJB";
	
	private static final String CONSUMER_SECRET = "yg8A9KM8C1PyzWTK0UZzZBfPmPs6cADpN5r39q0DLlyJSbA5Tn";
	
	public static String init(HttpServletRequest req) {
		
		StringBuffer callback = req.getRequestURL();
		String callBackHost = callback.toString();
		
		ConsumerCredentials credentials = new ConsumerCredentials(CONSUMER_KEY, CONSUMER_SECRET);
		
		OAuth1AuthorizationFlow flow = OAuth1ClientSupport
				.builder(credentials)
				.authorizationFlow(
						"https://api.twitter.com/oauth/request_token", 
						"https://api.twitter.com/oauth/access_token", 
						"https://api.twitter.com/oauth/authorize")
						.callbackUri(callBackHost)
						.build();
		
		String authorizationUri = flow.start();
		
		return null;
	}

}
