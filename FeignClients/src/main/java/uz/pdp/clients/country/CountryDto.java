package uz.pdp.clients.country;
//Sevinch Abdisattorova 04/15/2022 10:28 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class CountryDto {

    Integer id;

    String name;
}
