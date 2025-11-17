package Service;

import database.JsonDatabaseManager;
import model.Student;
import model.User;
import java.util.ArrayList;
import model.Instructor;
import requirements.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class AuthService extends User{
private JsonDatabaseManager db;
ArrayList<Student> students=db.viewStudents();
ArrayList<Instructor> instructors=db.viewInstructors();
    private final UserService userService;
    private User currentUser;

    public AuthService(UserService userService) {
        this.userService = userService;
    }
    public boolean LoginForStudent(String email, String password) throws NoSuchAlgorithmException {

        String hashpass=PasswordHasher.getHash(password);
        int flage=0;
for(int i=0;i<students.size();i++) {
    if (!(students.get(i).getEmail().equals(email))) {
        flage = 1;
        break;
    }
}
    int flagp =0;
    for (int j=0;j<students.size();j++)
    {
        if(students.get(j).getPasswordHash().equals(hashpass)){
            flagp =1;
            break;
        }
    }
   if(flage==1 && flagp==1) {
       System.out.println("Right Password");
       return true;

   }
   else{

       System.out.println("Wrong Password");
   return false;
   }

}
    public boolean LoginForInstructor(String email, String password) throws NoSuchAlgorithmException {

        String hashpass=PasswordHasher.getHash(password);
        int flage=0;
        for(int i=0;i<instructors.size();i++) {
            if (!(instructors.get(i).getEmail().equals(email))) {
                flage = 1;
                break;
            }
        }
        int flagp =0;
        for (int j=0;j<instructors.size();j++)
        {
            if(instructors.get(j).getPasswordHash().equals(hashpass)){
                flagp =1;
                break;
            }
        }
        if(flage==1 && flagp==1) {
            System.out.println("Right Password");
            return true;

        }
        else{

            System.out.println("Wrong Password");
            return false;
        }
//
    }
    }
