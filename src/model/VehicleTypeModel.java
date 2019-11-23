package model;

public class VehicleTypeModel {

    private String type;
    private String feature;
    private double weeklyRate;
    private double dailyRate;
    private double hourlyRate;
    private double weeklyInsurance;
    private double dailyInsurance;
    private double hourlyInsurance;
    private double kilometerRate;

    public VehicleTypeModel(String type, String feature, double weeklyRate, double dailyRate, double hourlyRate, double weeklyInsurance, double dailyInsurance, double hourlyInsurance, double kilometerRate) {
        this.type = type;
        this.feature = feature;
        this.weeklyRate = weeklyRate;
        this.dailyRate = dailyRate;
        this.hourlyRate = hourlyRate;
        this.weeklyInsurance = weeklyInsurance;
        this.dailyInsurance = dailyInsurance;
        this.hourlyInsurance = hourlyInsurance;
        this.kilometerRate = kilometerRate;
    }

    public String getType() {
        return type;
    }

    public String getFeature() {
        return feature;
    }

    public double getWeeklyRate() {
        return weeklyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getWeeklyInsurance() {
        return weeklyInsurance;
    }

    public double getDailyInsurance() {
        return dailyInsurance;
    }

    public double getHourlyInsurance() {
        return hourlyInsurance;
    }

    public double getKilometerRate() {
        return kilometerRate;
    }
}
