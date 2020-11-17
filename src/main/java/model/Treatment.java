package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Caregivers treat patients.
 */
public class Treatment {
    private  int lock;
    private long tid;
    private long pid;
    private long cid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    /**
     * constructs a treatment from the given params.
     *
     * @param pid
     * @param cid
     * @param lock
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long pid, long cid, int lock, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.pid = pid;
        this.cid = cid;
        this.lock = lock;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }
    /**
     * constructs a treatment from the given params.
     * @param tid
     * @param pid
     * @param cid
     * @param lock
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long tid, long pid, long cid, int lock, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.tid = tid;
        this.lock = lock;
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }
    /**
     *
     * @return treatment id
     */
    public long getTid() {
        return tid;
    }
    /**
     *
     * @return patient id
     */
    public long getPid() {
        return this.pid;
    }
    /**
     *
     * @return caregiver id
     */
    public long getCid() { return this.cid; }
    /**
     *
     * @return treatment date
     */
    public String getDate() {
        return date.toString();
    }
    /**
     *
     * @return treatment begin
     */
    public String getBegin() {
        return begin.toString();
    }
    /**
     *
     * @return treatment end
     */
    public String getEnd() {
        return end.toString();
    }
    /**
     *
     * @param cid
     *                    new caregiver int
     */
    public void setCid(int cid) {
        this.cid = cid;
    }
    /**
     *
     * @param s_date
     *                    new treatment date
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }
    /**
     *
     * @param begin
     *                    new treatment begin
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }
    /**
     *
     * @param end
     *                    new treatment end
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }
    /**
     *
     * @param lock
     *                    locks the Datensatz in accordance with the DSGVO
     */
    public void setLock(int lock){
        this.lock = lock;
    }
    /**
     *
     * @return treatment lock
     */
    public int getLock() { return lock;}
    /**
     *
     * @return treatment description
     */
    public String getDescription() {
        return description;
    }
    /**
     *
     * @param description
     *                    new treatment description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     *
     * @return treatment remarks
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     *
     * @param remarks
     *                    new treatment remakrs
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     *
     * @return treatment toString
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nCID: " + this.cid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}