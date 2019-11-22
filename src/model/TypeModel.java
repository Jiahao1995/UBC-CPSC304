package model;

public class TypeModel {

    private String type;
    private int num;
    private double price;

    public TypeModel(String type, int num, double price) {
        this.type = type;
        this.num = num;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public String getType() {
        return type;
    }
}
