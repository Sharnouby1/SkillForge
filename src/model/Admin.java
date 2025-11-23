package model;

import java.util.List;

public class Admin extends User{
    public Admin(String userId, String username, String email, String passwordHash) {
        this.setUserId(userId);
        this.setRole("Admin");
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);

    }
}
