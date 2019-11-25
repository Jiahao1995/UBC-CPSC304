package controller;

import database.DatabaseConnectionHandler;
import delegates.*;
import model.*;
import ui.*;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SuperRent implements
        OptionsDelegate,
        SearchDelegate,
        ReservationDelegate,
        ResultDelegate,
        ConfirmationDelegate,
        SignInDelegate,
        SignUpDelegate,
        RentDelegate,
        ReturnDelegate,
        DatabaseDelegate {

    private DatabaseConnectionHandler dbHandler;

    private String reserveType;
    private String reserveFromDate;
    private String reserveFromTime;
    private String reserveToDate;
    private String reserveToTime;
    private String confirmation;
    private String reservePhone;

    public SuperRent() {
        dbHandler = new DatabaseConnectionHandler();
        dbHandler.connectToOracle();
    }

    private void start() {
        OptionsUI optionsUI = new OptionsUI();
        optionsUI.showFrame(this);
        DatabaseUI databaseUI = new DatabaseUI();
        databaseUI.showFrame(this);
    }

    @Override
    public void search() {
        SearchUI searchUI = new SearchUI();
        searchUI.showFrame(this);
    }

    @Override
    public void reserve() {
        ReservationUI reservationUI = new ReservationUI();
        reservationUI.showFrame(this);
    }

    @Override
    public void reserve(String type, String fromDate, String fromTime, String toDate, String toTime) {
        if (!dbHandler.reserve(type))
            JOptionPane.showMessageDialog(
                    null,
                    "We do not have this vehicle type.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        else {
            reserveType = type;
            reserveFromDate = fromDate;
            reserveFromTime = fromTime;
            reserveToDate = toDate;
            reserveToTime = toTime;
            ConfirmationUI confirmationUI = new ConfirmationUI(
                    type, fromDate, fromTime, toDate, toTime);
            confirmationUI.showFrame(this);
        }
    }

    @Override
    public void rent() {
        int selection = JOptionPane.showConfirmDialog(
                null,
                "Do you have a reservation?",
                "Rent",
                JOptionPane.YES_NO_OPTION);
        if (selection == 0) {
            String inputConf = JOptionPane.showInputDialog(
                    null,
                    "Enter your confirmation number: ",
                    "Confirmation Number",
                    JOptionPane.INFORMATION_MESSAGE);
            ReservationModel rm = dbHandler.getReservationInfo(inputConf);
            if (rm == null)
                JOptionPane.showMessageDialog(
                        null,
                        "Confirmation number does not exist. Please check again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            else {
                String type = rm.getType();
                VehicleModel vm = dbHandler.delegateVehicle(type);
                if (vm == null)
                    JOptionPane.showMessageDialog(
                            null,
                            "No vehicle of this type remains.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                else {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                    String rentId = vm.getId() + dateFormat.format(date);
                    String cardNo = JOptionPane.showInputDialog(
                            null,
                            "Please enter your bank card number: ",
                            "Card Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    dbHandler.rent(rentId, vm.getId(), rm.getPhone(), rm.getFromDate(), rm.getFromTime(), rm.getToDate(), rm.getToTime(), vm.getOdometer(), cardNo, rm.getConfirmation());
                    String content = "Rent ID: " + rentId + "\n" +
                            "Vehicle ID: " + vm.getId() + "\n" +
                            "Vehicle Type: " + vm.getType() + "\n" +
                            "Customer: " + rm.getPhone() + "\n" +
                            "From: " + rm.getFromDate() + " " + rm.getFromTime() + "\n" +
                            "To: " + rm.getToDate() + " " + rm.getToTime() + "\n" +
                            "Odometer: " + vm.getOdometer() + "\n" +
                            "Card Number: " + cardNo;
                    JOptionPane.showMessageDialog(
                            null,
                            content,
                            "Receipt",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            RentUI rentUI = new RentUI();
            rentUI.showFrame(this);
        }
    }

    @Override
    public void rentReserve(String type, String phone, String fromDate, String fromTime, String toDate, String toTime, String cardNo) {
        VehicleModel vm = dbHandler.delegateVehicle(type);
        if (vm == null)
            JOptionPane.showMessageDialog(
                    null,
                    "No " + type + " remains.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        else {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String rentId = vm.getId() + dateFormat.format(date);
            dbHandler.rent(rentId, vm.getId(), phone, fromDate, fromTime, toDate, toTime, vm.getOdometer(), cardNo, "");
            String content = "Rent ID: " + rentId + "\n" +
                            "Vehicle ID: " + vm.getId() + "\n" +
                            "Vehicle Type: " + type + "\n" +
                            "Customer: " + phone + "\n" +
                            "From: " + fromDate + " " + fromTime + "\n" +
                            "To: " + toDate + " " + toTime + "\n" +
                            "Odometer: " + vm.getOdometer() + "\n" +
                            "Card Number: " + cardNo;
            JOptionPane.showMessageDialog(
                    null,
                    content,
                    "Receipt",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void rentalReport() {
        String date = JOptionPane.showInputDialog(
                null,
                "Please enter the date: ",
                "Report Date",
                JOptionPane.INFORMATION_MESSAGE);
        DailyModel[] dailyModels = dbHandler.dailyRentalReport(date, "");
        TypeModel[] typeModels = dbHandler.typeRentalReport(date, "");
        BranchModel[] branchModels = dbHandler.branchRentalReport(date, "");
        int num = dbHandler.getRentalNum(date, "");
        double revenue = 0.0;
        ReportUI reportUI = new ReportUI(
                "Rental",
                dailyModels,
                typeModels,
                branchModels,
                num,
                revenue);
        reportUI.showFrame();
    }

    @Override
    public void branchRentalReport() {
        String branch = JOptionPane.showInputDialog(
                null,
                "Please enter the branch: ",
                "Report Branch",
                JOptionPane.INFORMATION_MESSAGE);
        if (!dbHandler.checkBranch(branch)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Branch does not exist. Please check again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String date = JOptionPane.showInputDialog(
                null,
                "Please enter the date: ",
                "Report Date",
                JOptionPane.INFORMATION_MESSAGE);
        DailyModel[] dailyModels = dbHandler.dailyRentalReport(date, branch);
        TypeModel[] typeModels = dbHandler.typeRentalReport(date, branch);
        BranchModel[] branchModels = dbHandler.branchRentalReport(date, branch);
        int num = dbHandler.getRentalNum(date, branch);
        double revenue = 0.0;
        ReportUI reportUI = new ReportUI(
                "Rental",
                dailyModels,
                typeModels,
                branchModels,
                num,
                revenue);
        reportUI.showFrame();
    }

    @Override
    public void returnReport() {
        String date = JOptionPane.showInputDialog(
                null,
                "Please enter the date: ",
                "Report Date",
                JOptionPane.INFORMATION_MESSAGE);
        DailyModel[] dailyModels = dbHandler.dailyReturnReport(date, "");
        TypeModel[] typeModels = dbHandler.typeReturnReport(date, "");
        BranchModel[] branchModels = dbHandler.branchReturnReport(date, "");
        int num = dbHandler.getReturnNum(date, "");
        double revenue = dbHandler.getRevenue(date, "");
        ReportUI reportUI = new ReportUI(
                "Return",
                dailyModels,
                typeModels,
                branchModels,
                num,
                revenue);
        reportUI.showFrame();
    }

    @Override
    public void branchReturnReport() {
        String branch = JOptionPane.showInputDialog(
                null,
                "Please enter the branch: ",
                "Report Branch",
                JOptionPane.INFORMATION_MESSAGE);
        if (!dbHandler.checkBranch(branch)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Branch does not exist. Please check again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String date = JOptionPane.showInputDialog(
                null,
                "Please enter the date: ",
                "Report Date",
                JOptionPane.INFORMATION_MESSAGE);
        DailyModel[] dailyModels = dbHandler.dailyReturnReport(date, branch);
        TypeModel[] typeModels = dbHandler.typeReturnReport(date, branch);
        BranchModel[] branchModels = dbHandler.branchReturnReport(date, branch);
        int num = dbHandler.getReturnNum(date, branch);
        double revenue = dbHandler.getRevenue(date, branch);
        ReportUI reportUI = new ReportUI(
                "Return",
                dailyModels,
                typeModels,
                branchModels,
                num,
                revenue);
        reportUI.showFrame();
    }

    @Override
    public void view(String type, String location) {
        if (!type.equals("") && !dbHandler.checkType(type)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Vehicle type does not exist. Please check again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!location.equals("") && !dbHandler.checkBranch(location)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Branch does not exist. Please check again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        VehicleModel[] result = dbHandler.view(type, location);
        ResultUI resultUI = new ResultUI(result);
        resultUI.showFrame(this);
    }

    @Override
    public void confirm() {
        int selection = JOptionPane.showConfirmDialog(
                null,
                "Do you have a Super Rent account?",
                "Login",
                JOptionPane.YES_NO_OPTION);
        if (selection == 0) {
            SignInUI signInUI = new SignInUI();
            signInUI.showFrame(this);
        } else {
            SignUpUI signUpUI = new SignUpUI();
            signUpUI.showFrame(this);
        }
    }

    @Override
    public void signIn(String phone) {
        if (dbHandler.isPhoneExist(phone)) {
            reservePhone = phone;
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            confirmation = phone + dateFormat.format(date);
            String receiptInfo = "Confirmation Number: " + confirmation + "\n"
                    + "Vehicle Type: " + reserveType + "\n"
                    + "Customer: " + reservePhone + "\n"
                    + "From: " + reserveFromDate + " " + reserveFromTime + "\n"
                    + "To: " + reserveToDate + " " + reserveToTime;
            JOptionPane.showMessageDialog(
                    null,
                    receiptInfo,
                    "Receipt",
                    JOptionPane.INFORMATION_MESSAGE);
            dbHandler.recordReservation(confirmation, reserveType, reservePhone, reserveFromDate, reserveFromTime, reserveToDate, reserveToTime);
        } else
            JOptionPane.showMessageDialog(
                    null,
                    phone + " does not exist. Please try again or sign up.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void signUp(String phone, String name, String license, String address) {
        if (!dbHandler.isPhoneExist(phone)) {
            dbHandler.signUp(phone, name, license, address);
            reservePhone = phone;
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            confirmation = phone + dateFormat.format(date);
            String receiptInfo = "Confirmation Number: " + confirmation + "\n"
                    + "Vehicle Type: " + reserveType + "\n"
                    + "Customer: " + reservePhone + "\n"
                    + "From: " + reserveFromDate + " " + reserveFromTime + "\n"
                    + "To: " + reserveToDate + " " + reserveToTime;
            JOptionPane.showMessageDialog(
                    null,
                    receiptInfo,
                    "Receipt",
                    JOptionPane.INFORMATION_MESSAGE);
            dbHandler.recordReservation(confirmation, reserveType, reservePhone, reserveFromDate, reserveFromTime, reserveToDate, reserveToTime);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    phone + " already exists.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void returnBack(String id, String odometer, String full) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (!dbHandler.checkRent(id)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Only a rented vehicle can be returned!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dbHandler.isReturned(id)) {
            JOptionPane.showMessageDialog(
                    null,
                    "This vehicle has already been returned.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Random random = new Random();
        double price = random.nextDouble() * (200 - 50) + 50;
        dbHandler.returnBack(id, dateFormat.format(date), timeFormat.format(time), odometer, full, price);

        String content = "Confirmation Number: " + dbHandler.getConfirmation(id) + "\n"
                    + "Date: " + dateFormat.format(date) + "\n"
                    + "Time: " + timeFormat.format(time) + "\n"
                    + "Price: " + df.format(price) + "\n"
                    + " = dailyRate + dailyInsuranceRate * (toDate - fromDate)";
        JOptionPane.showMessageDialog(
                null,
                content,
                "Receipt",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void returnBack() {
        ReturnUI returnUI = new ReturnUI();
        returnUI.showFrame(this);
    }

    @Override
    public void showVehicleTypes() {
        VehicleTypeModel[] models = dbHandler.showVehicleTypes();
        ShowVehicleTypesUI ui = new ShowVehicleTypesUI(models);
        ui.showFrame();
    }

    @Override
    public void showVehicles() {
        VehicleModel[] models = dbHandler.showVehicles();
        ShowVehiclesUI ui = new ShowVehiclesUI(models);
        ui.showFrame();
    }

    @Override
    public void showCustomers() {
        CustomerModel[] models = dbHandler.showCustomers();
        ShowCustomersUI ui = new ShowCustomersUI(models);
        ui.showFrame();
    }

    @Override
    public void showReservations() {
        ReservationModel[] models = dbHandler.showReservations();
        ShowReservationsUI ui = new ShowReservationsUI(models);
        ui.showFrame();
    }

    @Override
    public void showRentals() {
        RentalModel[] models = dbHandler.showRentals();
        ShowRentalsUI ui = new ShowRentalsUI(models);
        ui.showFrame();
    }

    @Override
    public void showReturns() {
        ReturnModel[] models = dbHandler.showReturns();
        ShowReturnsUI ui = new ShowReturnsUI(models);
        ui.showFrame();
    }

    public static void main(String[] args) {
        SuperRent superRent = new SuperRent();
        superRent.start();
    }

}
