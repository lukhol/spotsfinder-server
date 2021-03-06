package com.lukhol.spotsfinder.geolocation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.lukhol.spotsfinder.exception.GeocodingCityException;
import com.lukhol.spotsfinder.exception.NotFoundGeocodingInformationException;
import com.lukhol.spotsfinder.model.GeocodingInformation;
import com.lukhol.spotsfinder.service.GeocodingService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FetchCityComponent {

	private static final String GOOGLE_MAPS_GEOCODING_API_KEY = "AIzaSyCScQ7EgUqnPkOcxCvf_X7qOOEHIV0t74o";
	private final GeocodingService geocodingService;
	
	@Autowired
	public FetchCityComponent(@NonNull GeocodingService geocodingService){
		this.geocodingService = geocodingService;
	}
	
	public double[] fetchCityCoordinates(String cityName) throws GeocodingCityException {
		
		try {
			GeocodingInformation geocodingInformation = geocodingService.findBySearchingPhrase(cityName);
			return new double[] { geocodingInformation.getLatitude(), geocodingInformation.getLongitude() };
		} catch (NotFoundGeocodingInformationException ex) {
			log.info("Not found GeocodingInformation for searching phrase {}. Google api will be called", cityName);
		}
		
		GeoApiContext context = new GeoApiContext().setApiKey(GOOGLE_MAPS_GEOCODING_API_KEY);
		
		GeocodingResult[] results;
		try {
			results = GeocodingApi.newRequest(context).address(cityName).await();
			GeocodingResult firstGeocodingResult = results[0];
			
			double latitude = firstGeocodingResult.geometry.location.lat;
			double longitude = firstGeocodingResult.geometry.location.lng;
			
			createAndSaveNewGeocodingInformation(results, cityName);
			
			return new double[] { latitude, longitude };
		} catch (ApiException | InterruptedException | IOException e) {
			log.error("Error while searching location coordinates of given city.");
			log.error(e.getMessage());
			
			throw new GeocodingCityException(e.getMessage());
		}
	}
	
	private void createAndSaveNewGeocodingInformation(GeocodingResult[] results, String searchingPhrase){
		
		try {
			GeocodingResult result = results[0];
			
			double latitude = result.geometry.location.lat;
			double longitude = result.geometry.location.lng;
			String oryginalCityName = result.addressComponents[0].longName;
			
			ObjectMapper objectMaper = new ObjectMapper();
			String resultJson = objectMaper.writeValueAsString(results);
			
			GeocodingInformation geocodingInformation = 
					new GeocodingInformation(searchingPhrase, oryginalCityName, resultJson, longitude, latitude);
			
			geocodingService.save(geocodingInformation);
		} catch (Exception e){
			log.error("Exception occured inside FetchCityComponent - createAndSaveNewGeocodingInformation. Message: {}", e.getMessage());
		}
	}
}
