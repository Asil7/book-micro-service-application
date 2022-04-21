package uz.pdp.clients.country;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("country")
public interface CountryClient {
    @GetMapping("/api/country/{countryId}")
    CountryDto getCountryById(@PathVariable Integer countryId);

    @GetMapping("/api/country/continent/{continentId}")
    List<CountryDto> getCountriesByContinent(@PathVariable Integer continentId);
}
