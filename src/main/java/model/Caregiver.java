package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver extends Person {

    private int cid;
    private String firstName;
    private String surname;
    private String telephone;
    private List<Caregiver> allCaregiver = new ArrayList<Caregiver>();

    /**
     * constructs a caregiver from the given params
     * @param firstName
     * @param surname
     * @param telephone
     */
    public Caregiver (String firstName, String surname, String telephone) {
        super(firstName, surname);
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
    }
    public Caregiver (int Cid, String firstName, String surname, String telephone) {
        super(firstName, surname);
        this.cid = Cid;
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
    }

    public long getCid() {
        return this.cid;
    }
    public String getFirstname() {
      return firstName;
    }
    public String getSurname() {
      return surname;
    }
    public String getTelephone() { return telephone;}
    
    
    public void setCid(int id) {
      this.cid = id;
  }
    public void setFirstname(String firstname) {
      this.firstName = firstname;
  }
    public void setLastname(String surname) {
      this.surname = surname;
  }
    public void setTelephone(String telephone) {
      this.telephone = telephone;
  }



    }