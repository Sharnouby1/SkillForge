package requirements;

import java.util.Random;

public class IdGenerator {

    public static String generateStudentID(){
        return "S"  + new Random().nextInt(90000);
    }

    public static String generateInstructorID(){
        return "I"  + new Random().nextInt(90000);
    }

    public static String generateCourseID(){
        return "C"  + new Random().nextInt(90000);
    }

    public static String generateLessonID(){
        return "L"  + new Random().nextInt(90000);
    }
}
