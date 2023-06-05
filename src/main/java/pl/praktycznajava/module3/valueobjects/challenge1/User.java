package pl.praktycznajava.module3.valueobjects.challenge1;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class User {

    String id;
    String name;
    String surname;
    DateOfBirth dateOfBirth;
    Address Address;
}
