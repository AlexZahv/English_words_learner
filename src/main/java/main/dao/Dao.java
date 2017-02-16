package main.dao;

import main.entities.Word;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface Dao<T> {
    Word get(T t) throws SQLException;

    long save(T t) throws SQLException;

    List<T> getAll(T t);

    int update(T t);

    void delete(T t) throws SQLException;

    List<T> getRandom(T t,int count);
}
