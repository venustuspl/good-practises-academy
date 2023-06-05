package pl.praktycznajava.module3.valueobjects.challenge1;

import lombok.Value;

@Value(staticConstructor = "of")
public class Address {
    String street;
    String postalCode;
    String city;
    String country;

    public String getFormattedAddress(String userId) {
        return this.getCountry() + ", " + this.getCity() + ", " + this.getStreet() + " " + this.getPostalCode();
    }
}

