package service;

import model.Lesson;
import model.Course;
import model.Student;


public class LessonService {

    public Lesson createLesson(String lessonID, String title, String content,Course course){
        return new Lesson(lessonID,title,content);
    }

    public static void fetchLesson(Lesson lesson,Course course) {
       course.getLessons().add(lesson);
    }

    public static void markAsCompleted(Lesson lesson) {
        lesson.setCompleted();
    }
}
