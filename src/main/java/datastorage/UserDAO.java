package datastorage;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific user-SQL-queries.
 */
public class UserDAO extends DAOimp<User> {

    /**
     * constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public UserDAO(Connection conn) {
        super(conn);
    }

    /*protected String getCreateStatementString(Person person) {
        return null;
    }*/

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given user
     * @param user for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO user (firstname, surname, password) VALUES ('%s', '%s', '%s')",
                user.getFirstName(), user.getSurname(), user.getPassword());

    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM user WHERE uid = %d", key);
    }

    /**
     * generates a <code>select</code>-Statement for a password given a uid
     * @param uid for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    public String getPasswordByID(int uid) {
        return String.format("SELECT password FROM user WHERE uid = %d", uid);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>User</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return user with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        User c = null;
        c = new User(result.getInt(1), result.getString(2),
                result.getString(3),result.getString(4));
        return c;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all user.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>user-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to user-object.
     * @return ArrayList with Users from the resultSet.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        User c = null;
        while (result.next()) {
            c = new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4));
            list.add(c);
        }
        return list;
    }

    /*protected String getUpdateStatementString(Person person) {
        return null;
    }*/

    /**
     * generates a <code>UPDATE</code>-Statement for a given patient
     * @param user for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */

    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET firstname = '%s', surname = '%s', password = '%s'" +
                        "WHERE uid = %d", user.getFirstName(), user.getSurname(), user.getPassword(), user.getUid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM user WHERE uid=%d", key);
    }


}
