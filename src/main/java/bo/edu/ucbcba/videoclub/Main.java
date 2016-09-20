package bo.edu.ucbcba.videoclub;

import bo.edu.ucbcba.videoclub.view.InitialForm;
import bo.edu.ucbcba.videoclub.view.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        LoginForm form = new LoginForm();
        //InitialForm form = new InitialForm();
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
