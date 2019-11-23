package model;

public class CustomerModel {

    private final String phone;
    private final String name;
    private final String license;
    private final String address;

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getAddress() {
        return address;
    }

    public CustomerModel(String phone, String name, String license, String address) {
        this.phone = phone;
        this.name = name;
        this.license = license;
        this.address = address;
    }
}
