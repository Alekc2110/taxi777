package my.fin.project.model.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  interface Mapper
 * @param <T>
 */
public interface Mapper<T> {
    T getEntity(ResultSet resultSet) throws SQLException;
}
