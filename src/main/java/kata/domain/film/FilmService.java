package kata.domain.film;

import java.util.Optional;

public class FilmService {
    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public Optional<Film> findById(String title) {
        return repository.findById(title);
    }

    public void save(Film film) {
        repository.save(film.title, film);
    }
}
