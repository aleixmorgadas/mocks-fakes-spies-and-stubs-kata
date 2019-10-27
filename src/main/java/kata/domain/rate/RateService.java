package kata.domain.rate;

import kata.domain.film.Film;
import kata.domain.film.FilmService;
import kata.domain.user.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RateService {
    static final int RATES_PER_FILM_FOR_NOTIFICATION = 10;

    private final RateRepository repository;
    private final FilmService filmService;
    private final LikedNotifier likedNotifier;

    public RateService(RateRepository repository, FilmService filmService, LikedNotifier likedNotifier) {
        this.repository = repository;
        this.filmService = filmService;
        this.likedNotifier = likedNotifier;
    }

    public void save(Rate rate) {
        repository.save(rate.id, rate);

        if (repository.ratesForFilm(rate.title).size() == RATES_PER_FILM_FOR_NOTIFICATION) {
            likedNotifier.notifyForFilm(rate.title);
        }
    }

    public Optional<Rate> findById(RateId id) {
        return repository.findById(id);
    }

    public List<Rate> findByUser(UserId userId) {
        return repository.all().stream().filter(rate -> rate.by(userId)).collect(Collectors.toList());
    }

    public List<Rate> ratedByUserAtYearOrMoreRecent(UserId userId, int productionYear) {
        return findByUser(userId).stream()
                .filter(rate -> {
                    final Optional<Film> filmOptional = filmService.findById(rate.title);
                    return filmOptional.isPresent() && filmOptional.get().releaseDate >= productionYear;
                })
                .collect(Collectors.toList());
    }
}
