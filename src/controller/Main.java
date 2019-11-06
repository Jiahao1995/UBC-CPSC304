package controller;

import delegates.IdentityDelegate;
import delegates.LoginDelegate;
import delegates.SearchDelegate;
import delegates.SignUpDelegate;
import ui.IdentityUI;
import ui.LoginUI;
import ui.SearchUI;
import ui.SignUpUI;

public class Main implements
        LoginDelegate,
        SignUpDelegate,
        IdentityDelegate,
        SearchDelegate {

    private LoginUI loginUI;
    private SignUpUI signUpUI;
    private IdentityUI identityUI;
    private SearchUI searchUI;

    public Main() {
        loginUI = new LoginUI();
        loginUI.showFrame(this);
    }

    @Override
    public void signIn(String username, String password) {
        identityUI = new IdentityUI();
        identityUI.showFrame(this);
    }

    @Override
    public void signUp() {
        signUpUI = new SignUpUI();
        signUpUI.showFrame(this);
    }

    @Override
    public void signUp(String username, String password, String confirmPassword) {

    }

    @Override
    public void chooseCustomer() {
        searchUI = new SearchUI();
        searchUI.showFrame(this);
    }

    @Override
    public void chooseClerk() {
    }

    @Override
    public void reset() {

    }

    @Override
    public void search(String fromDate,
                       String fromTime,
                       String toDate,
                       String toTime,
                       String carType,
                       String location) {

    }

    public static void main(String[] args) {
        Main main = new Main();
    }

}
