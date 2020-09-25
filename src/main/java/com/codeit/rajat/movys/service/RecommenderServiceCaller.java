package com.codeit.rajat.movys.service;

import java.net.URISyntaxException;
import java.rmi.ServerException;

import javax.annotation.PostConstruct;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Python model endpoint service caller
 * @author rajat
 *
 */
@Service
public class RecommenderServiceCaller
{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;

	private String BASE_URI = "";
	
	
	
	public String getBASE_URI()
	{
		return BASE_URI;
	}

	public void setBASE_URI(String bASE_URI)
	{
		BASE_URI = bASE_URI;
	}

	@PostConstruct
	private void init()
	{
		System.out.println("Init Recommendation Caller.");
		// get uri from properties file
		this.setBASE_URI(environment.getRequiredProperty("recommender.service.uri"));
	}

	/**
	 * 
	 * API Endpoint health check. Called from AppInitializr.class
	 * 
	 * @return string, status code
	 * @throws URISyntaxException
	 * @throws ServerException
	 */
	public ResponseEntity<String> healthCheck() throws URISyntaxException, ServerException
	{
		try
		{
			ResponseEntity<String> status = restTemplate.exchange(this.getBASE_URI(), HttpMethod.GET, null, String.class);
			return status;
		} catch (RestClientException e)
		{
			throw new ServerException("Model server not responding");
		}
	}

	/**
	 * 
	 * Send request to python API and get recommended movies JSON
	 * 
	 * @param originalTitle
	 * @return JSON string
	 * @throws JSONException
	 */
	public JSONObject getRecommendation(String originalTitle) throws JSONException
	{
		String requestUrl = this.getBASE_URI() + "/get_recommendation/" + originalTitle.toLowerCase();
		String result = restTemplate.getForObject(requestUrl, String.class);
		return new JSONObject(result);
	}
}
