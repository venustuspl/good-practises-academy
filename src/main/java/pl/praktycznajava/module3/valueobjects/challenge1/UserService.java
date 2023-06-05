package pl.praktycznajava.module3.valueobjects.challenge1;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UserService {

    public static final int ADULT_AGE = 18;
    UserRepository userRepository;

    public boolean isBirthdayToday(String userId) {
        User user = userRepository.findBy(userId);
        LocalDate currentDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
        return currentDate.getMonth() == user.getDateOfBirth().getMonth() && currentDate.getDayOfMonth() == user.getDateOfBirth().getDayOfMonth();
    }

    public boolean isAdult(String userId) {
        User user = userRepository.findBy(userId);
        LocalDate currentDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
        return currentDate.getYear() - user.getDateOfBirth().getYear() > ADULT_AGE;
    }
    
    public String getFormattedAddress(String userId) {
        UserAddress userAddress = userRepository.findBy(userId).getUserAddress();
        return userAddress.getCountry() + ", " + userAddress.getCity() + ", " + userAddress.getStreet() + " " + userAddress.getPostalCode();
    }

}