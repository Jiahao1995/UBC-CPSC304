package database;

import model.*;
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
            Statement statement = connection.createStatement();
            String sql;
            if (type.equals("") && location.equals(""))
                sql = "SELECT VEHICLE_ID, TYPE_NAME, MAKE, MODEL, VEHICLE_YEAR, COLOR, ODOMETER, BRANCH FROM VEHICLES";
            else if (type.equals(""))
                sql = "SELECT VEHICLE_ID, TYPE_NAME, MAKE, MODEL, VEHICLE_YEAR, COLOR, ODOMETER, BRANCH FROM VEHICLES WHERE BRANCH = '" + location + "'";
            else if (location.equals(""))
                sql = "SELECT VEHICLE_ID, TYPE_NAME, MAKE, MODEL, VEHICLE_YEAR, COLOR, ODOMETER, BRANCH FROM VEHICLES WHERE TYPE_NAME = '" + type + "'";
            else
                sql = "SELECT VEHICLE_ID, TYPE_NAME, MAKE, MODEL, VEHICLE_YEAR, COLOR, ODOMETER, BRANCH FROM VEHICLES WHERE BRANCH = '" + location + "'" + " AND TYPE_NAME = '" + type +"'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                VehicleModel model = new VehicleModel(
                        rs.getString("vehicle_id"),
                        rs.getString("type_name"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_year"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getString("branch"),
                        "",
                        "");
                result.add(model);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return result.toArray(new VehicleModel[result.size()]);
    }

    public boolean reserve(String type) {
        boolean result;
        try {
            String sql = "SELECT * FROM VEHICLES WHERE TYPE_NAME = '" + type + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            result = rs.next();

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public ReservationModel getReservationInfo(String confirmation) {
        ReservationModel model = null;
        try {
            String sql = "SELECT * FROM RESERVATIONS WHERE CONFIRMATION = '" + confirmation + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next())
                model = new ReservationModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return model;
    }

    public VehicleModel delegateVehicle(String type) {
        VehicleModel model = null;
        try {
            String sql = "SELECT VEHICLE_ID, TYPE_NAME, MAKE, MODEL, VEHICLE_YEAR, COLOR, ODOMETER, BRANCH FROM VEHICLES WHERE TYPE_NAME = '" + type + "' AND VEHICLE_STATUS = '1'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next())
                model = new VehicleModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        "",
                        "");
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return model;
    }

    public boolean isPhoneExist(String phone) {
        boolean result;
        try {
            String sql = "SELECT * FROM CUSTOMERS WHERE PHONE = '" + phone + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            result = rs.next();

            rs.close();
            statement.close();

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

    public boolean checkRent(String rentId) {
        boolean result;
        try {
            String sql = "SELECT * FROM RENTALS WHERE RENT_ID = '" + rentId + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            result = rs.next();

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public boolean isReturned(String rendId) {
        boolean result;
        try {
            String sql = "SELECT * FROM RETURN_BACK WHERE RENT_ID = '" + rendId + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            result = rs.next();

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public boolean checkBranch(String branch) {
        boolean result;
        try {
            String sql = "SELECT * FROM VEHICLES WHERE BRANCH = '" + branch + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            result = rs.next();

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public boolean checkType(String type) {
        boolean result;
        try {
            String sql = "SELECT * FROM VEHICLE_TYPES WHERE TYPE_NAME = '" + type + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            result = rs.next();

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void rent(String rentId, String vehicleId, String phone, String fromDate, String fromTime, String toDate, String toTime, int odometer, String cardNo, String confirmation) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RENTALS VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, rentId);
            ps.setString(2, vehicleId);
            ps.setString(3, phone);
            ps.setString(4, fromDate);
            ps.setString(5, fromTime);
            ps.setString(6, toDate);
            ps.setString(7, toTime);
            ps.setInt(8, odometer);
            ps.setString(9, cardNo);
            ps.setString(10, confirmation);
            ps.executeUpdate();

            PreparedStatement ps1 = connection.prepareStatement("UPDATE VEHICLES SET VEHICLE_STATUS = '0' WHERE VEHICLE_ID = ?");
            ps1.setString(1, vehicleId);
            ps1.executeUpdate();

            connection.commit();

            ps.close();
            ps1.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void returnBack(String rentId, String date, String time, String odometer, String fullTank, double price) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RETURN_BACK VALUES (?,?,?,?,?,?)");
            ps.setString(1, rentId);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, odometer);
            ps.setString(5, fullTank);
            ps.setDouble(6, price);
            ps.executeUpdate();

            String sql = "UPDATE VEHICLES SET VEHICLE_STATUS = '1' WHERE VEHICLES.VEHICLE_ID = (SELECT RENTALS.VEHICLE_ID FROM RENTALS WHERE RENTALS.RENT_ID = '" + rentId + "')";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);

            connection.commit();

            statement.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public String getConfirmation(String rentId) {
        String result = "";
        try {
            String sql = "SELECT CONFIRMATION FROM RENTALS WHERE RENT_ID = '" + rentId + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next())
                result = rs.getString(1);

            rs.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return result;
        }
    }

    // rental report
    public DailyModel[] dailyRentalReport(String date, String branch) {
        ArrayList<DailyModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.BRANCH, V.TYPE_NAME, R.RENT_ID " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID AND FROM_DATE = '" + date + "' " +
                    "ORDER BY V.BRANCH ASC, V.VEHICLE_ID ASC";
        else
            sql = "SELECT V.BRANCH, V.TYPE_NAME, R.RENT_ID " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID AND FROM_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "ORDER BY V.BRANCH ASC, V.VEHICLE_ID ASC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                DailyModel model = new DailyModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new DailyModel[models.size()]);
    }

    public TypeModel[] typeRentalReport(String date, String branch) {
        ArrayList<TypeModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.TYPE_NAME, COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "' " +
                    "GROUP BY V.TYPE_NAME";
        else
            sql = "SELECT V.TYPE_NAME, COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "GROUP BY V.TYPE_NAME";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TypeModel model = new TypeModel(
                        rs.getString(1),
                        rs.getInt(2),
                        0.0);
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new TypeModel[models.size()]);
    }

    public BranchModel[] branchRentalReport(String date, String branch) {
        ArrayList<BranchModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.BRANCH, COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "' " +
                    "GROUP BY V.BRANCH";
        else
            sql = "SELECT V.BRANCH, COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "GROUP BY V.BRANCH";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                BranchModel model = new BranchModel(
                        rs.getString(1),
                        rs.getInt(2),
                        0.0);
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new BranchModel[models.size()]);
    }

    // return report
    public DailyModel[] dailyReturnReport(String date, String branch) {
        ArrayList<DailyModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.BRANCH, V.TYPE_NAME, RB.RENT_ID " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "ORDER BY V.BRANCH ASC, V.TYPE_NAME ASC";
        else
            sql = "SELECT V.BRANCH, V.TYPE_NAME, RB.RENT_ID " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "ORDER BY V.BRANCH ASC, V.TYPE_NAME ASC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                DailyModel model = new DailyModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new DailyModel[models.size()]);
    }

    public int getRentalNum(String date, String branch) {
        int num = 0;
        String sql;
        if (branch.equals(""))
            sql = "SELECT COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "'";
        else
            sql = "SELECT COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND R.FROM_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                num = rs.getInt(1);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return num;
    }

    public int getReturnNum(String date, String branch) {
        int num = 0;
        String sql;
        if (branch.equals(""))
            sql = "SELECT COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V, RETURN_BACK RB " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RENT_ID = R.RENT_ID " +
                    "AND RB.RETURN_DATE = '" + date + "'";
        else
            sql = "SELECT COUNT(*) " +
                    "FROM RENTALS R, VEHICLES V, RETURN_BACK RB " +
                    "WHERE R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RENT_ID = R.RENT_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                num = rs.getInt(1);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return num;
    }

    public double getRevenue(String date, String branch) {
        double revenue = 0;
        String sql;
        if (branch.equals(""))
            sql = "SELECT SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "'";
        else
            sql = "SELECT SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                revenue = rs.getDouble(1);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return revenue;
    }

    public TypeModel[] typeReturnReport(String date, String branch) {
        ArrayList<TypeModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.TYPE_NAME, COUNT(*), SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "GROUP BY V.TYPE_NAME";
        else
            sql = "SELECT V.TYPE_NAME, COUNT(*), SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "GROUP BY V.TYPE_NAME";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TypeModel model = new TypeModel(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDouble(3));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new TypeModel[models.size()]);
    }

    public BranchModel[] branchReturnReport(String date, String branch) {
        ArrayList<BranchModel> models = new ArrayList<>();
        String sql;
        if (branch.equals(""))
            sql = "SELECT V.BRANCH, COUNT(*), SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "GROUP BY V.BRANCH";
        else
            sql = "SELECT V.BRANCH, COUNT(*), SUM(RB.PRICE) " +
                    "FROM RETURN_BACK RB, RENTALS R, VEHICLES V " +
                    "WHERE RB.RENT_ID = R.RENT_ID " +
                    "AND R.VEHICLE_ID = V.VEHICLE_ID " +
                    "AND RB.RETURN_DATE = '" + date + "' " +
                    "AND V.BRANCH = '" + branch + "' " +
                    "GROUP BY V.BRANCH";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                BranchModel model = new BranchModel(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDouble(3));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new BranchModel[models.size()]);
    }

    public VehicleTypeModel[] showVehicleTypes() {
        ArrayList<VehicleTypeModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VEHICLE_TYPES";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                VehicleTypeModel model = new VehicleTypeModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new VehicleTypeModel[models.size()]);
    }

    public VehicleModel[] showVehicles() {
        ArrayList<VehicleModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VEHICLES";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                VehicleModel model = new VehicleModel(
                        rs.getString(1),
                        rs.getString(9),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(10),
                        rs.getString(8),
                        rs.getString(2));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new VehicleModel[models.size()]);
    }

    public CustomerModel[] showCustomers() {
        ArrayList<CustomerModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CUSTOMERS";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                CustomerModel model = new CustomerModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new CustomerModel[models.size()]);
    }

    public ReservationModel[] showReservations() {
        ArrayList<ReservationModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM RESERVATIONS";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ReservationModel model = new ReservationModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new ReservationModel[models.size()]);
    }

    public RentalModel[] showRentals() {
        ArrayList<RentalModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM RENTALS";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                RentalModel model = new RentalModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new RentalModel[models.size()]);
    }

    public ReturnModel[] showReturns() {
        ArrayList<ReturnModel> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM RETURN_BACK";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ReturnModel model = new ReturnModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6));
                models.add(model);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return models.toArray(new ReturnModel[models.size()]);
    }

}
