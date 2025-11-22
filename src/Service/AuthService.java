package Service;

import database.JsonDatabaseManager;
import model.Student;
import model.User;
import java.util.ArrayList;
import model.Instructor;
import requirements.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class AuthService extends User{
    private static JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");

static ArrayList<Student> students=db.viewStudents();
static ArrayList<Instructor> instructors=db.viewInstructors();
    private final UserService userService;
    private User currentUser;

    public AuthService(UserService userService) {
        this.userService = userService;
    }
    public static boolean LoginForStudent(String email, String password) throws NoSuchAlgorithmException {

        String hashpass = PasswordHasher.getHash(password);

        for (Student s : students) {
            if (s.getEmail().equals(email) && s.getPasswordHash().equals(hashpass)) {
                System.out.println("Right Password");
                return true;
            }
        }

        System.out.println("Wrong Password");
        return false;
    }

    public static boolean LoginForInstructor(String email, String password) throws NoSuchAlgorithmException {

        String hashpass=PasswordHasher.getHash(password);
         for(Instructor i:instructors){
             if(i.getEmail().equals(email) && hashpass.equals(i.getPasswordHash())){
                 System.out.println("Right Password");
                 return true;
             }
         }

            System.out.println("Wrong Password");
            return false;


    }
    }
