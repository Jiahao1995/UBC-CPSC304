package model;

public class BranchModel {

    private String branch;
    private int num;
    private double price;

    public BranchModel(String branch, int num, double price) {
        this.branch = branch;
        this.num = num;
        this.price = price;
    }

    public String getBranch() {
        return branch;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }
}
