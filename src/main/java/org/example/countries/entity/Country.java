package org.example.countries.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commonName;
    private String officialName;
    private String capital;
    private String region;
    private String subregion;
    private Long population;

    public Country(String commonName, String officialName, String capital, String region, String subregion, Long population) {
        this.commonName = commonName;
        this.officialName = officialName;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
    }
}
