package kata.domain.rate;

import support.Repository;

import java.util.List;

public interface RateRepository extends Repository<Rate, RateId> {
    List<Rate> all();

    List<Rate> ratesForFilm(String title);
}
