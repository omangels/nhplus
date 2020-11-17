package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class User extends Person {

    private int uid;
    private String firstName;
    private String surname;
    private String password;

    /**
     * constructs a user from the given params
     * @param firstName
     * @param surname
     * @param password
     */
    public User(String firstName, String surname, String password) {
        super(firstName, surname);
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
    }
    public User(int Uid, String firstName, String surname, String password) {
        super(firstName, surname);
        this.uid = Uid;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
    }


    /**
     *
     * @return uid
     */
    public long getUid() {
        return this.uid;
    }
    /**
     *
     * @return firstname
     */
    public String getFirstname() {
      return firstName;
    }
    /**
     *
     * @return surname
     */
    public String getSurname() {
      return surname;
    }
    /**
     *
     * @return password
     */
    public String getPassword() { return password;}

    /**
     *
     * @param id
     *                    new id
     */
    public void setUid(int id) {
      this.uid = id;
  }
    /**
     *
     * @param firstname
     *                    new first name
     */
    public void setFirstname(String firstname) {
      this.firstName = firstname;
  }
    /**
     *
     * @param surname
     *                    new surname
     */
    public void setLastname(String surname) {
      this.surname = surname;
  }
    /**
     *
     * @param password
     *                    new password
     */
    public void setPassword(String password) {
      this.password = password;
  }



    }