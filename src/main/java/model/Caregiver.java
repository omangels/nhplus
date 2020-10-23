package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver extends Person {

    private int cid;
    private String firstName;
    private String surname;
    private int telephone;
    private List<Caregiver> allCaregiver = new ArrayList<Caregiver>();

    /**
     * constructs a caregiver from the given params.
     * @param cid
     * @param firstName
     * @param surname
     * @param telephone
     */
    public Caregiver (int cid, String firstName, String surname, int telephone) {
        super(firstName, surname);
        this.cid = cid;
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
    }

    public long getCID() {
        return this.cid;
    }
    public String getFirstname() {
      return firstName;
    }
    public String getSurname() {
      return surname;
    }
    public long getTelephone() {
      return telephone;
    }
    
    
    public void setCID(int id) {
      this.cid = id;
  }
    public void setFirstname(String firstname) {
      this.firstName = firstname;
  }
    public void setLastname(String surname) {
      this.surname = surname;
  }
    public void setTelephone(int telephone) {
      this.telephone = telephone;
  }



    }