package org.example.countries.client;

import org.example.countries.dto.CountryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "countryClient", url = "https://restcountries.com/v3.1")
public interface CountryClient {

    @GetMapping("/all")
    List<CountryDto> getAllCountries(@RequestParam("fields") String fields);

    @GetMapping("/name/{name}")
    List<CountryDto> getCountryByName(@PathVariable("name") String name);
}
