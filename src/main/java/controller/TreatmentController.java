package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreatmentController {
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private Label lblCaregiverSurname;
    @FXML
    private Label lblCaregiverFirstname;
    @FXML
    private Label lblCaregiverTelephone;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> comboBox;
    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;
    private Caregiver caregiver;
    private ObservableList<Caregiver> tableviewContent =
            FXCollections.observableArrayList();
    private CaregiverDAO dao;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;


    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        createComboBoxData();
        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);

        this.stage = stage;
        this.controller= controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.caregiver = cDao.read((int) treatment.getCid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * displaying of the data of a treatment
     */
    private void showData(){
        this.lblPatientName.setText(patient.getSurname()+", "+patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
        this.lblCaregiverSurname.setText(this.caregiver.getSurname());
        this.lblCaregiverFirstname.setText(this.caregiver.getFirstname());
        this.lblCaregiverTelephone.setText(this.caregiver.getTelephone());
    }

    /**
     * handles the change of a treatment
     */
    @FXML
    public void handleChange(){
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        this.treatment.setCid(searchInList(comboBox.getSelectionModel().getSelectedItem()).getCid());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * changes the treatment in the database
     */
    private void doUpdate(){
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds caregivers surnames to a combo box
     */
    private void createComboBoxData(){
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver: caregiverList) {
                this.myComboBoxData.add(caregiver.getSurname()); //Oder vielleicht doch nur die ID? Was sagt das Datenschutzgesetz?
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * handles the canceling of an action
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }

    /**
     * handles the combo box
     */
    @FXML
    public void handleComboBox(){
        String c = this.comboBox.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregiver;
        if(c.equals("alle")){
            try {
                allCaregiver= this.dao.readAll();
                for (Caregiver caregiver : allCaregiver) {
                    this.tableviewContent.add(caregiver);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        caregiver = searchInList(c);
    }

    /**
     * searches for a caregiver in a certain list
     * @param surname surname of the caregiver
     * @return the found caregiver
     */
    private Caregiver searchInList(String surname){
        for (int i =0; i<this.caregiverList.size();i++){
            if(this.caregiverList.get(i).getSurname().equals(surname)){
                return this.caregiverList.get(i);
            }
        }
        return null;
    }

    /**
     * handles the locking of data
     */
    @FXML
    public void handleLock(){
        this.treatment.setLock(1);
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
    }
}
