package model;

public class DailyModel {

    private String branch;
    private String type;
    private String id;

    public DailyModel(String branch, String type, String id) {
        this.branch = branch;
        this.type = type;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBranch() {
        return branch;
    }
}
