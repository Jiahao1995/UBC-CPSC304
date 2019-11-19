package controller;

import database.DatabaseConnectionHandler;
import delegates.*;
import model.VehicleModel;
import ui.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuperRent implements
        OptionsDelegate,
        SearchDelegate,
        ReservationDelegate,
        ResultDelegate,
        ConfirmationDelegate,
        SignInDelegate,
        SignUpDelegate {

    private DatabaseConnectionHandler dbHandler;

    // reserve information
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
    public void rent() {}

    @Override
    public void rentalReport() {}

    @Override
    public void branchRentalReport() {}

    @Override
    public void returnBack() {}

    @Override
    public void returnReport() {}

    @Override
    public void branchReturnReport() {}

    @Override
    public void view(String type, String location) {
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
                JOptionPane.YES_OPTION,
                JOptionPane.NO_OPTION);
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
                    JOptionPane.YES_OPTION);
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
                    JOptionPane.YES_OPTION);
            dbHandler.recordReservation(confirmation, reserveType, reservePhone, reserveFromDate, reserveFromTime, reserveToDate, reserveToTime);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    phone + " already exists.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SuperRent superRent = new SuperRent();
        superRent.start();
    }

}
