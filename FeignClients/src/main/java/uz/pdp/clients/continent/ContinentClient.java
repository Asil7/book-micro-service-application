package uz.pdp.clients.continent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("continent")
public interface ContinentClient {
    @GetMapping("/api/continent/{continentId}")
    ContinentDto getContinentById(@PathVariable Integer continentId);
}
