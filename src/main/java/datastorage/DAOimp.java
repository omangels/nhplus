package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * implements the DAO
 */
public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    public DAOimp(Connection conn) {
        this.conn = conn;
    }
    /**
     * runs an sql statement
     * @param t
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }
    /**
     * returns object from an sql statement
     * @param key
     * @return object
     */
    @Override
    public T read(int key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }
    /**
     * runs a result list from an sql statement
     * @return list
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }
    /**
     * runs an sql statement and updates a database entry
     * @param t
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }
    /**
     * deletes a database entry
     * @param key
     */
    @Override
    public void deleteById(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }
    /**
     * returns an CREATE-Statement
     * @return String
     *
     */
    protected abstract String getCreateStatementString(T t);
    /**
     * returns an read by id statement string
     * @param key
     * @return String
     */
    protected abstract String getReadByIDStatementString(int key);
    /**
     * returns an instance from a result set
     * @param set
     * @return T
     */
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;
    /**
     * returns an read all statement string
     * @return String
     */
    protected abstract String getReadAllStatementString();
    /**
     * returns an list from a result set
     * @param set
     * @return ArrayList
     */
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    /**
     * returns an update statement string
     * @param t
     * @return String
     */
    protected abstract String getUpdateStatementString(T t);
    /**
     * returns an delete statement string
     * @return String
     */
    protected abstract String getDeleteStatementString(int key);


}
