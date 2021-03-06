package controller;

import datastorage.PatientDAO;
import datastorage.CaregiverDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import datastorage.DAOFactory;
import java.sql.SQLException;
import java.util.List;


/**
 * The <code>AllPatientController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, Integer> colID;
    @FXML
    private TableColumn<Caregiver, String> colFirstName;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, Integer> colTelephone;

    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txfFirstName;
    @FXML
    TextField txfSurname;
    @FXML
    TextField txfTelephone;

    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();
        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid")); // Ab hier macht der Fehler. Keine Ahnung, was das Problem ist.

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("telephone"));

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * call update in {@link CaregiverDAO} and edits a caregiver
     */
    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link CaregiverDAO} and shows caregiver in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregiver;
        try {
            allCaregiver = dao.readAll();
            for (Caregiver c : allCaregiver) {
                this.tableviewContent.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link CaregiverDAO}
     */
    @FXML
    public void handleDeleteRow() {
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCaregiverDAO();
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        System.out.println("Selected Item: " + selectedItem.getFirstname());
        this.tableView.getItems().remove(selectedItem);
        try {
            dao.deleteById((int) selectedItem.getCid());
            cDao.deleteById((int) selectedItem.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // this.handleAdd();
    }

    /**
     * handles a add-click-event. Creates a caregiver and calls the create method in the {@link CaregiverDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txfSurname.getText();
        String firstname = this.txfFirstName.getText();
        String telephone = this.txfTelephone.getText();
        try {
            Caregiver c = new Caregiver(firstname, surname, telephone);
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();

    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txfFirstName.clear();
        this.txfSurname.clear();
        this.txfTelephone.clear();
    }
}