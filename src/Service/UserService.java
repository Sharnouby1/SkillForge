package Service;
import requirements.PasswordHasher;
import model.Student;
import model.Instructor;
import database.JsonDatabaseManager;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserService {

    private JsonDatabaseManager db;
ArrayList<Student> students=db.viewStudents();
    ArrayList<Instructor> instructors=db.viewInstructors();

    public UserService(JsonDatabaseManager db) {
          this.db=db;
    }
public boolean StudentsEmailExists(String email){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
}
public boolean InstructorEmailExists(String email )
{
    for(int i=0;i<instructors.size();i++){
        if(instructors.get(i).getEmail().equals(email)) {
            return true;
        }
    }
return false;
}
public boolean StudentPassExists (String password) throws NoSuchAlgorithmException
{String passhash=PasswordHasher.getHash(password);
    for(int i=0;i<students.size();i++){
        if(students.get(i).getPasswordHash().equals(passhash)) {
            return true;
        }
    }
    return false;
}
    public boolean InstructorPassExists (String password) throws NoSuchAlgorithmException
    {String passhash=PasswordHasher.getHash(password);
        for(int i=0;i<instructors.size();i++){
            if(instructors.get(i).getPasswordHash().equals(passhash)) {
                return true;
            }
        }
        return false;
    }

}