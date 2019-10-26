package kata.domain.rate;

import com.github.javafaker.Faker;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RateDummy {
    private final static Random randomNumber = new Random();

    public static RateBuilder randomRate() {

        return createRate()
                .withTitle(Faker.instance().funnyName().name())
                .withScore(randomNumber.nextInt(5) + 1)
                .withUserId(UserIdDummy.randomUserId());
    }

    public static List<Rate> randomListOfRatesOfSize(int size) {
        final List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rates.add(randomRate().build());
        }
        return rates;
    }

    public static RateBuilder createRate() {
        return new RateBuilder();
    }

    static class RateBuilder {
        private String title;
        private int score;
        private UserId userId;

        RateBuilder() {
        }

        public RateBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public RateBuilder withScore(int score) {
            this.score = score;
            return this;
        }

        public RateBuilder withUserId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Rate build() {
            return new Rate(title, score, userId);
        }
    }
}
