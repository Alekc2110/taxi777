package my.fin.project.model.db.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> extends AutoCloseable {

    Optional<Long> save(T entity);

    Optional<T> getById(Long id);

    List<T> findAll();

    boolean update(T t);

    void close();

}
