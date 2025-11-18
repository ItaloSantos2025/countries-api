package org.example.countries.service;

import org.example.countries.client.CountryClient;
import org.example.countries.dto.CountryDto;
import org.example.countries.entity.Country;
import org.example.countries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryClient countryClient;

    @Autowired
    private CountryRepository countryRepository;

    public void fetchAndSaveCountries() {
        String fields = "name,capital,region,subregion,population";
        List<CountryDto> countryDTOs = countryClient.getAllCountries(fields);
        List<Country> countries = countryDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        countryRepository.saveAll(countries);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<Country> getCountryByName(String name) {
        return countryRepository.findByCommonNameContainingIgnoreCase(name);
    }

    public void fetchAndSaveCountryByName(String name) {
        List<CountryDto> countryDTOs = countryClient.getCountryByName(name);
        List<Country> countries = countryDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        countryRepository.saveAll(countries);
    }

    public List<CountryDto> getCountryFromExternalApi(String name) {
        return countryClient.getCountryByName(name);
    }

    private Country convertToEntity(CountryDto dto) {
        String capital = (dto.getCapital() != null && dto.getCapital().length > 0) ? Arrays.toString(dto.getCapital()) : "N/A";
        capital = capital.replace("[", "").replace("]", "");
        return new Country(
                dto.getName().getCommon(),
                dto.getName().getOfficial(),
                capital,
                dto.getRegion(),
                dto.getSubregion(),
                dto.getPopulation()
        );
    }
}
