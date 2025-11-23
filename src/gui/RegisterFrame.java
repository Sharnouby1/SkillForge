package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import database.JsonDatabaseManager;
import requirements.Validator;
import service.AuthService;
import service.UserService;


public class RegisterFrame extends JFrame {
    private JPanel Container1;
    private JRadioButton studentRadioButton;
    private JRadioButton instructorRadioButton;
    private JButton signUpButton;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JTextField email;
    private JRadioButton adminRadioButton;

    public RegisterFrame() {
        setTitle("Welcome");
        setContentPane(Container1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);


        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        studentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentRadioButton.isSelected()) {
                    instructorRadioButton.setSelected(false);
                    adminRadioButton.setSelected(false);

                }
            }
        });
        instructorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instructorRadioButton.isSelected()) {
                    studentRadioButton.setSelected(false);
                    adminRadioButton.setSelected(false);
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Validator validator = new Validator();
                if(!validator.validEmail(email.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid E-mail");
                }
               else if(passwordField1.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid password");
                }
else if(studentRadioButton.isSelected()==false && instructorRadioButton.isSelected()==false && adminRadioButton.isSelected()==false ) {
                JOptionPane.showMessageDialog(null, "Please Choose Student or Instructor or Admin");
}
else if(studentRadioButton.isSelected())
{
    try {
        if(AuthService.LoginForStudent(email.getText(),passwordField1.getText())) {

            dispose();
            JOptionPane.showMessageDialog(null, "Login Successful");
            new StudentMenu();
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong Email or Password");
        }
    } catch (NoSuchAlgorithmException ex) {

        throw new RuntimeException(ex);
    }
}
               else if(instructorRadioButton.isSelected())
                {
                    try {
                        if(AuthService.LoginForInstructor(email.getText(),passwordField1.getText())) {
                            dispose();
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            JsonDatabaseManager db = new JsonDatabaseManager("users.json","courses.json");
                            UserService us = new UserService();
                            new InstructorFrame(db, us.getInstructor(email.getText()));
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Wrong Email or Password");
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                }
               else if(adminRadioButton.isSelected())
                {
                    try {
                        if(AuthService.LoginForAdmin(email.getText(),passwordField1.getText())) {
                            dispose();
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            JsonDatabaseManager db = new JsonDatabaseManager("users.json","courses.json");
                            UserService us = new UserService();
                            new AdminDashboard();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Wrong Email or Password");
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong Email or Password");
                }
            }
        });
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentRadioButton.isSelected()==false && instructorRadioButton.isSelected()==false )
                    JOptionPane.showMessageDialog(null, "Please Choose Student Or Instructor To Sign Up");
                    else if(studentRadioButton.isSelected())
                    {
                        dispose();
                        new signup();
                    } else if (instructorRadioButton.isSelected()) {

                    dispose();
                    new signupins();
                }
            }
        });
        adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adminRadioButton.isSelected())
                {
                    studentRadioButton.setSelected(false);
                    instructorRadioButton.setSelected(false);
                }
            }
        });
    }
}
