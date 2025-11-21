package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import Service.*;
import requirements.*;
import model.*;
import database.*;
public class signup extends JFrame {
    private JTextField username;
    private JTextField email;
    private JTextField password;
    private JButton confirmButton;
    private JPanel Container1;
    private JButton returnButton;

    public signup() {
        setTitle("Welcome");
        setContentPane(Container1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Validator validator = new Validator();

                if (username.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                }
                else if (!validator.validEmail(email.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid E-mail");
                }
                else if (password.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters");
                }
                else {

                    UserService us = new UserService();

                    if (!us.StudentsEmailExists(email.getText())) {

                        IdGenerator id = new IdGenerator();
                        String ids = id.generateStudentID();

                        String hash = null;
                        try {
                            hash = PasswordHasher.getHash(password.getText());
                        } catch (NoSuchAlgorithmException ex) {
                            throw new RuntimeException(ex);
                        }

                        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");


                        Student s = new Student(
                                ids,
                                "student",
                                username.getText(),
                                email.getText(),
                                hash,
                                null,     // ← zero enrolled courses
                                "0"      // ← zero progress
                        );

                        db.addUser(s);
                        dispose();
                        new RegisterFrame();

                    } else {
                        JOptionPane.showMessageDialog(null, "Email already exists!");
                    }
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterFrame();
            }
        });
    }
}
