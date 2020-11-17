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



    public long getUid() {
        return this.uid;
    }
    public String getFirstname() {
      return firstName;
    }
    public String getSurname() {
      return surname;
    }
    public String getPassword() { return password;}
    
    
    public void setUid(int id) {
      this.uid = id;
  }
    public void setFirstname(String firstname) {
      this.firstName = firstname;
  }
    public void setLastname(String surname) {
      this.surname = surname;
  }
    public void setPassword(String password) {
      this.password = password;
  }



    }