package service;

import model.Course;
import model.Lesson;
import model.Student;

import java.util.List;

public class CourseAnalytics {

    // -------------------------------------------------------
    //   1) Calculate completion rate for the course
    // -------------------------------------------------------
    // completion rate = total completed lessons (all students) /
    //                   (total lessons * enrolled students)
    public static double getCourseCompletionRate(Course course, List<Student> allStudents) {
        int studentCount = course.getEnrolledStudentIDs().size();
        int lessonCount = course.getLessons().size();

        if (studentCount == 0 || lessonCount == 0) return 0.0;

        int totalPossible = studentCount * lessonCount;
        int totalCompleted = 0;

        for (String studentID : course.getEnrolledStudentIDs()) {
            Student st = findStudentByID(studentID, allStudents);
            if (st != null) {
                for (Lesson lesson : course.getLessons()) {
                    if (st.hasCompletedLesson(lesson.getLessonID())) {
                        totalCompleted++;
                    }
                }
            }
        }

        return (double) totalCompleted / totalPossible;
    }

    // -------------------------------------------------------
    //   2) Average quiz score for the course
    // -------------------------------------------------------
    public static double getAverageCourseScore(Course course) {
        double total = 0.0;
        int count = 0;

        for (Lesson lesson : course.getLessons()) {
            total += lesson.getAverageScore();
            if (lesson.getAverageScore() > 0)
                count++;
        }

        if (count == 0) return 0.0;

        return total / count;
    }

    // -------------------------------------------------------
    //   3) Count how many students completed the whole course
    // -------------------------------------------------------
    public static int getStudentsFinishedCourse(Course course, List<Student> students) {
        int completed = 0;
        List<Lesson> lessons = course.getLessons();

        for (String studentID : course.getEnrolledStudentIDs()) {
            Student st = findStudentByID(studentID, students);
            if (st != null) {
                boolean finished = true;

                for (Lesson lesson : lessons) {
                    if (!st.hasCompletedLesson(lesson.getLessonID())) {
                        finished = false;
                        break;
                    }
                }

                if (finished) completed++;
            }
        }
        return completed;
    }

    // -------------------------------------------------------
    //   4) Total quiz attempts in the course
    // -------------------------------------------------------
    public static int getTotalQuizAttempts(Course course) {
        int attempts = 0;

        for (Lesson l : course.getLessons()) {
            attempts += l.getQuizAttempts();
        }

        return attempts;
    }

    // -------------------------------------------------------
    //   Helper: find student by ID
    // -------------------------------------------------------
    private static Student findStudentByID(String id, List<Student> students) {
        for (Student s : students) {
            if (s.getUserId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
