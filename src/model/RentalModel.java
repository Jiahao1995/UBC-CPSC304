package model;

public class RentalModel {

    private final String rentId;
    private final String vehicleId;
    private final String phone;
    private final String fromDate;
    private final String fromTime;
    private final String toDate;
    private final String toTime;
    private final String odometer;
    private final String cardNo;
    private final String confirmation;

    public RentalModel(String rentId, String vehicleId, String phone, String fromDate, String fromTime, String toDate, String toTime, String odometer, String cardNo, String confirmation) {
        this.rentId = rentId;
        this.vehicleId = vehicleId;
        this.phone = phone;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.odometer = odometer;
        this.cardNo = cardNo;
        this.confirmation = confirmation;
    }

    public String getRentId() {
        return rentId;
    }

    public String getVehicleId() {
        return vehicleId;
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

    public String getOdometer() {
        return odometer;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getConfirmation() {
        return confirmation;
    }
}
