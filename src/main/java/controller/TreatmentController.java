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
    private ObservableList<Caregiver> tableviewContent =
            FXCollections.observableArrayList();
    private CaregiverDAO dao;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;
    private Caregiver caregiver;

    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        createComboBoxData();
        System.out.println(myComboBoxData.get(0));
        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);

        this.stage = stage;
        this.controller= controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showData(){
        this.lblPatientName.setText(patient.getSurname()+", "+patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
    }

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

    private void doUpdate(){
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
    @FXML
    public void handleCancel(){
        stage.close();
    }
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
    private Caregiver searchInList(String surname){
        for (int i =0; i<this.caregiverList.size();i++){
            if(this.caregiverList.get(i).getSurname().equals(surname)){
                return this.caregiverList.get(i);
            }
        }
        return null;
    }

    @FXML
    public void handleLock(){
        this.treatment.setLock(1);
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
    }
}
