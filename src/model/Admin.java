package model;

public class Admin extends User{

    public Admin(String userId, String username, String email, String passwordHash, String hash) {
        this.setUserId(userId);
        this.setRole("Admin");
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);

    }
}
