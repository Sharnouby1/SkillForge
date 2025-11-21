package Service;

import requirements.PasswordHasher;
import model.Student;
import model.Instructor;
import database.JsonDatabaseManager;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserService {

    private JsonDatabaseManager db;

    public UserService() {
        db = new JsonDatabaseManager("users.json", "courses.json");
    }

    private ArrayList<Student> getStudents() {
        return db.viewStudents();
    }

    private ArrayList<Instructor> getInstructors() {
        return db.viewInstructors();
    }

    public boolean StudentsEmailExists(String email) {
        for (Student s : getStudents()) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean InstructorEmailExists(String email) {
        for (Instructor i : getInstructors()) {
            if (i.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean StudentPassExists(String password) throws NoSuchAlgorithmException {
        String passhash = PasswordHasher.getHash(password);

        for (Student s : getStudents()) {
            if (s.getPasswordHash().equals(passhash)) {
                return true;
            }
        }
        return false;
    }

    public boolean InstructorPassExists(String password) throws NoSuchAlgorithmException {
        String passhash = PasswordHasher.getHash(password);

        for (Instructor i : getInstructors()) {
            if (i.getPasswordHash().equals(passhash)) {
                return true;
            }
        }
        return false;
    }
}
