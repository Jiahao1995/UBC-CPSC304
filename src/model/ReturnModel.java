package model;

public class ReturnModel {

    private final String id;
    private final String date;
    private final String time;
    private final String odometer;
    private final String full;
    private final double price;

    public ReturnModel(String id, String date, String time, String odometer, String full, double price) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.odometer = odometer;
        this.full = full;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getOdometer() {
        return odometer;
    }

    public String getFull() {
        return full;
    }

    public double getPrice() {
        return price;
    }
}
