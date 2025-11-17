package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import Service.AuthService;
import requirements.*;

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
                if(username.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                }
                else if(!validator.validEmail(email.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid E-mail");
                }
                else if(password.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid password");
                }
                else {
                    try {
                        if (AuthService.LoginForStudent(email.getText(), password.getText())) {
                            dispose();
                            new RegisterFrame();
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        JOptionPane.showMessageDialog(null, "This email has already been used" );
                        new signup();
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
