package org.example.countries.service;

import org.example.countries.client.CountryClient;
import org.example.countries.dto.CountryDTO;
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
        List<CountryDTO> countryDTOs = countryClient.getAllCountries(fields);
        List<Country> countries = countryDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        countryRepository.saveAll(countries);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<CountryDTO> getCountryByName(String name) {
        return countryClient.getCountryByName(name);
    }

    public void fetchAndSaveCountryByName(String name) {
        List<CountryDTO> countryDTOs = countryClient.getCountryByName(name);
        List<Country> countries = countryDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        countryRepository.saveAll(countries);
    }

    public List<CountryDTO> getCountryFromExternalApi(String name) {
        return countryClient.getCountryByName(name);
    }

    private Country convertToEntity(CountryDTO dto) {
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
