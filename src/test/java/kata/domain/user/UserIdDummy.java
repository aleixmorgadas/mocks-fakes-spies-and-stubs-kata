package kata.domain.user;

import com.github.javafaker.Faker;

public class UserIdDummy {
    public static UserId randomUserId() {
        return UserId.of(Faker.instance().name().username());
    }
}
