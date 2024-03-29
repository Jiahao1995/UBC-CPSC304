package model;

public class VehicleModel {

    private final String id;
    private final String type;
    private final String make;
    private final String model;
    private final String year;
    private final String color;
    private final int odometer;
    private final String branch;

    private String status;
    private String license;

    public VehicleModel(String id, String type, String make, String model, String year, String color, int odometer, String branch, String status, String license) {
        this.id = id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.branch = branch;
        this.status = status;
        this.license = license;
    }

    public String getBranch() {
        return branch;
    }

    public String getStatus() {
        return status;
    }

    public String getLicense() {
        return license;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getColor() {
        return color;
    }

    public String getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
