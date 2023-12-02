package personal.project.youtube.service;

import java.util.Optional;

public interface BaseService<T> {
    T save(T entity);

    Optional<T> findById(Long id);
}
