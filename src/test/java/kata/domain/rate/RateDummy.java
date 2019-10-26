package kata.domain.rate;

import com.github.javafaker.Faker;
import kata.domain.user.UserIdDummy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RateDummy {

    public static Rate random() {
        final Random randomNumber = new Random();
        return Rate.of(Faker.instance().funnyName().name(), randomNumber.nextInt(5) + 1, UserIdDummy.randomUserId());
    }

    public static List<Rate> randomListOfRatesOfSize(int size) {
        final List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rates.add(random());
        }
        return rates;
    }
}
