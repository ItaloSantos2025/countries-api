package org.example.countries.controller;

import org.example.countries.dto.CountryDto;
import org.example.countries.entity.Country;
import org.example.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<Void> fetchAndSaveCountries() {
        countryService.fetchAndSaveCountries();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Country>> getCountryByName(@PathVariable String name) {
        List<Country> countries = countryService.getCountryByName(name);
        return ResponseEntity.ok(countries);
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> fetchAndSaveCountryByName(@PathVariable String name) {
        countryService.fetchAndSaveCountryByName(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/external/{name}")
    public ResponseEntity<List<CountryDto>> getCountryFromExternalApi(@PathVariable String name) {
        List<CountryDto> countries = countryService.getCountryFromExternalApi(name);
        return ResponseEntity.ok(countries);
    }
}
