package database;

import model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String ORACLE_USERNAME = "ora_jiahao95";
    private static final String ORACLE_PASSWORD = "a50462670";

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean connectToOracle() {
        try {
            if (connection != null)
                connection.close();
            connection = DriverManager.getConnection(ORACLE_URL, ORACLE_USERNAME, ORACLE_PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public VehicleModel[] view(String type, String location) {
        ArrayList<VehicleModel> result = new ArrayList<VehicleModel>();

        try {
            PreparedStatement ps;
            if (type.equals("") && location.equals(""))
                ps = connection.prepareStatement("SELECT vehicle_id, type_name, make, model, year, color, odometer, branch FROM vehicles");
            else if (type.equals("")) {
                ps = connection.prepareStatement("SELECT vehicle_id, type_name, make, model, year, color, odometer, branch FROM vehicles WHERE branch = ?");
                ps.setString(1, location);
            } else if (location.equals("")) {
                ps = connection.prepareStatement("SELECT vehicle_id, type_name, make, model, year, color, odometer, branch FROM vehicles WHERE type_name = ?");
                ps.setString(1, type);
            } else {
                ps = connection.prepareStatement("SELECT vehicle_id, type_name, make, model, year, color, odometer, branch FROM vehicles WHERE branch = ? AND type_name = ?");
                ps.setString(2, type);
                ps.setString(1, location);
            }
            ps.executeUpdate();
            connection.commit();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VehicleModel model = new VehicleModel(
                        rs.getString("vehicle_id"),
                        rs.getString("type_name"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("year"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getString("branch"));
                result.add(model);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return result.toArray(new VehicleModel[result.size()]);
    }

    public boolean reserve(String type) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vehicles WHERE type_name = ?");
            ps.setString(1, type);

            ps.executeUpdate();
            connection.commit();
            ResultSet rs = ps.executeQuery();

            ps.close();

            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public boolean isPhoneExist(String phone) {
        boolean result;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM customers WHERE phone = ?");
            ps.setString(1, phone);

            ps.executeUpdate();
            connection.commit();
            ResultSet rs = ps.executeQuery();
            result = rs.next();

            rs.close();
            ps.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public void signUp(String phone, String name, String license, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            ps.setString(1, phone);
            ps.setString(2, name);
            ps.setString(3, license);
            ps.setString(4, address);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void recordReservation(String confirmation, String type, String phone, String fromDate, String fromTime, String toDate, String toTime) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO reservations VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, confirmation);
            ps.setString(2, type);
            ps.setString(3, phone);
            ps.setString(4, fromDate);
            ps.setString(5, fromTime);
            ps.setString(6, toDate);
            ps.setString(7, toTime);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }



}
