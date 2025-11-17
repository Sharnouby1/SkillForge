package requirements;

public class Validator {

    public static boolean validEmail(String email) {
        if(email == null) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean validUserName(String userName) {
        if(userName == null || userName.length() < 3){
            return false;
        }
        String regex = "^[A-Za-z0-9_-]{3,}";
        return userName.matches(regex);
    }

    public static boolean validPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public static boolean validCourseID(String courseID) {
        if(courseID == null){
            return false;
        }
        String regex = "^C\\d{1,5}$";
        return courseID.matches(regex);
    }

    public static boolean validStudentID(String studentID) {
        if(studentID == null){
            return false;
        }
        String regex = "^S\\d{1,5}$";
        return studentID.matches(regex);
    }

    public static boolean validInstructorID(String instructorID) {
        if(instructorID == null){
            return false;
        }
        String regex = "^I\\d{1,5}$";
        return instructorID.matches(regex);
    }

    public static boolean validLessonID(String lessonID) {
        if(lessonID == null){
            return false;
        }
        String regex = "^L\\d{1,5}$";
        return lessonID.matches(regex);
    }
}
