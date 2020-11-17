package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Caregivers work in a NURSING home and are treating patiens.
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
    /**
     * constructs a caregiver from the given params
     * @param cid
     * @param firstName
     * @param surname
     * @param telephone
     */
    public Caregiver (int cid, String firstName, String surname, String telephone) {
        super(firstName, surname);
        this.cid = cid;
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
    }
    /**
     *
     * @return caregiver id
     */
    public int getCid() {
        return this.cid;
    }
    /**
     *
     * @return caregiver firstname
     */
    public String getFirstname() {
      return firstName;
    }
    /**
     *
     * @return caregiver surname
     */
    public String getSurname() {
      return surname;
    }    /**
     *
     * @return caregiver telephone
     */
    public String getTelephone() { return telephone;}

    /**
     *
     * @param id
     *                    new cid
     */
    public void setCid(int id) {
      this.cid = id;
  }
    /**
     *
     * @param firstname
     *                    new firstname
     */
    public void setFirstname(String firstname) {
      this.firstName = firstname;
  }
    /**
     *
     * @param surname
     *                    new surname
     */
    public void setSurname(String surname) {
      this.surname = surname;
  }
    /**
     *
     * @param telephone
     *                    new telephone
     */
    public void setTelephone(String telephone) {
      this.telephone = telephone;
  }



    }