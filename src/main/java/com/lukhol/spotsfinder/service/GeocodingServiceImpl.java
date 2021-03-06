package com.lukhol.spotsfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lukhol.spotsfinder.exception.NotFoundGeocodingInformationException;
import com.lukhol.spotsfinder.model.GeocodingInformation;
import com.lukhol.spotsfinder.repository.GeocodingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeocodingServiceImpl implements GeocodingService{

	private final GeocodingRepository geocodingRepository;

	@Override
	@Transactional
	public GeocodingInformation save(GeocodingInformation geocodingInformation) {
		GeocodingInformation gi = geocodingRepository.save(geocodingInformation);
		log.info("GeocodingInformation for city {} has been saved to the database.", geocodingInformation.getOryginalCityName());
		return gi;
	}

	@Override
	@Transactional
	public GeocodingInformation findBySearchingPhrase(String searchingPhrase) throws NotFoundGeocodingInformationException {
		GeocodingInformation geocodingInformation =  geocodingRepository.findBySearchingPhraseIgnoringCase(searchingPhrase);
		
		if(geocodingInformation != null)
			return geocodingInformation;
		
		geocodingInformation = geocodingRepository.findByOryginalCityNameIgnoringCase(searchingPhrase);
		
		if(geocodingInformation == null)
			throw new NotFoundGeocodingInformationException(String.format("Not found GeocodingInformation for: %s.", searchingPhrase));
		
		return geocodingInformation;
	}

}
