package my.fin.project.model.db.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> extends AutoCloseable {

    Long save(T entity);

    T getById(Long id);

    List<T> findAll();

    boolean update(T t);

    boolean delete(Long id);

    void close();

}
