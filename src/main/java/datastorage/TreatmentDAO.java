package datastorage;

import model.Treatment;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreatmentDAO extends DAOimp<Treatment> {

    public TreatmentDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, cid, lock, treatment_date, begin, end, description, remarks) VALUES " +
                "(%d, '%d', '0', '%s', '%s', '%s', '%s', '%s')", treatment.getPid(), treatment.getCid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d AND WHERE lock = 0", key);
    }

    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(5));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(6));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(7));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2), result.getLong(3), 0,
                date, begin, end, result.getString(8), result.getString(9));
        return m;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM treatment WHERE lock = 0";
    }

    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(5));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(6));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(7));
            t = new Treatment(result.getLong(1), result.getLong(2), result.getLong(3), 0,
                    date, begin, end, result.getString(8), result.getString(9));
            list.add(t);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, cid = %d, lock = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s' WHERE tid = %d", treatment.getPid(), treatment.getCid(), treatment.getLock(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getTid());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    public void deleteByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }
    public void deleteByExpiration() throws SQLException {
        Statement st = conn.createStatement();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todayDate = formatter.format(date);
        String expirationDate = todayDate.replace(todayDate.substring(0, todayDate.indexOf('-')), String.valueOf(Integer.parseInt(todayDate.substring(0, todayDate.indexOf('-')))-10));
        st.executeUpdate(String.format("DELETE FROM treatment WHERE TREATMENT_DATE < '%s'", expirationDate));
    }
}