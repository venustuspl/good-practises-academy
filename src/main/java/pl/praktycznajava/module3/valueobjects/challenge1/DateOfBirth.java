package pl.praktycznajava.module3.valueobjects.challenge1;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Setter
@Value(staticConstructor = "of")
public class DateOfBirth {
    public static final int ADULT_AGE = 18;
    LocalDate dateOfBirth;

    UserRepository userRepository;

    public boolean isBirthdayToday() {
        LocalDate currentDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
        return currentDate.getMonth() == this.getDateOfBirth().getMonth() && currentDate.getDayOfMonth() == this.getDateOfBirth().getDayOfMonth();
    }

    public boolean isAdult() {
        LocalDate currentDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
        return currentDate.getYear() - this.getDateOfBirth().getYear() > ADULT_AGE;
    }
}
