package datastorage;

import java.sql.SQLException;
import java.util.List;
/**
 * The interface of Data Access Objects which are engaging with the database via sql statements.
 */
public interface DAO<T> {
    /**
     * Creates a DAO
     * @param t
     */
    void create(T t) throws SQLException;
    /**
     * Reads a DAO
     * @param key
     */
    T read(int key) throws SQLException;
    /**
     * reads all DAOs
     */
    List<T> readAll() throws SQLException;
    /**
     * Updates a DAO
     * @param t
     */
    void update(T t) throws SQLException;
    /**
     * Deletes a DAO
     * @param key
     */
    void deleteById(int key) throws SQLException;
}
