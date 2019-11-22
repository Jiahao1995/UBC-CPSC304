package model;

public class ReservationModel {

    private String confirmation;
    private String type;
    private String phone;
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;

    public ReservationModel(String confirmation, String type, String phone, String fromDate, String fromTime, String toDate, String toTime) {
        this.confirmation = confirmation;
        this.type = type;
        this.phone = phone;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToDate() {
        return toDate;
    }

    public String getToTime() {
        return toTime;
    }
}
