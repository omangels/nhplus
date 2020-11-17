package model;
/**
 * Persons are abstract. You need to create a caregiver, patient or user.
 */
public abstract class Person {
    private String firstName;
    private String surname;

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }
    /**
     *
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     *
     * @param firstName
     *                    new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * @param surname
     *                    new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
