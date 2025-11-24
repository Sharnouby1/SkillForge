package model;

public class Admin extends User{

    public Admin(String userId, String role, String username, String email, String passwordHash) {
        setUserId(userId);
        setRole(role);
        setUsername(username);
        setEmail(email);
        setPasswordHash(passwordHash);
    }

}
