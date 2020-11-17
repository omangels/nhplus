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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NewTreatmentController {
    public TextField txtPfleger;
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
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
    private ComboBox<String> comboBox;

    private ObservableList<Caregiver> tableviewContent =
            FXCollections.observableArrayList();
    private CaregiverDAO dao;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;

    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;
    private Caregiver caregiver;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);
        showPatientData();
        createComboBoxData();
    }

    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    @FXML
    public void handleAdd(){


            LocalDate date = this.datepicker.getValue();
            String s_begin = txtBegin.getText();
            LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
            String description = txtDescription.getText();
            String remarks = taRemarks.getText();
            Treatment treatment = new Treatment(patient.getPid(), caregiver.getCid(), 0, date,
                    begin, end, description, remarks);
            try {
                createTreatment(treatment);
            }
            catch(NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Pfleger für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Pfleger aus!");
            alert.showAndWait();
        }

            controller.readAllAndShowInTableView();
            stage.close();
        }

    //test
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        System.out.println(caregiver.getSurname());
        stage.close();
    }


    private void createComboBoxData(){
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver: caregiverList) {
                System.out.println(caregiver.getFirstname());
                this.myComboBoxData.add(caregiver.getSurname()); //Oder vielleicht doch nur die ID? Was sagt das Datenschutzgesetz?
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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
}