package datastorage;

import model.User;
/**
 * The Data Access Object Factory for Treatment DAOs, Caregiver DAOs, Patient DAOs and User DAOs
 */
public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }
    /**
     * returns a DAO instance
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    /**
     * @return TreatmentDAO
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }
    /**
     * @return PatientDAO
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }
    /**
     * @return CaregiverDAO
     */
    public CaregiverDAO createCaregiverDAO() { return new CaregiverDAO(ConnectionBuilder.getConnection()); }
    /**
     * @return UserDAO
     */
    public UserDAO createUserDAO() { return new UserDAO(ConnectionBuilder.getConnection()); }
}