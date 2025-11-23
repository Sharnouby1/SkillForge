package gui;

import database.JsonDatabaseManager;

import javax.swing.*;
import database.JsonDatabaseManager;
import model.*;
import java.awt.*;
import java.security.*;

public class AdminDashboard extends  JFrame {
    private JComboBox PendingCourses;
    private JTextArea courseTitle;
    private JTextArea descritpion;
    private JComboBox lessons;
    private JButton declineButton;
    private JButton backButton;
    private JButton approveButton;

    private JsonDatabaseManager db;
    private Admin admin;

    public AdminDashboard(JsonDatabaseManager db, Admin admin) {
    this.db = db;
    this.admin = admin;
        setTitle("Admin Dashboard - " + admin.getUsername());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
