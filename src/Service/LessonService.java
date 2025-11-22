package Service;

import model.Lesson;
import model.Course;
import requirements.Validator;



public class LessonService {

    private CourseService courseService = new CourseService();

    public LessonService(CourseService courseService) {
        this.courseService = courseService;
    }

    public Lesson createLesson(String lessonID, String title, String content){
        if(Validator.validLessonID(lessonID)){
        return new Lesson(lessonID,title,content);
        }
        return null;
    }

    public void fetchLesson(Lesson lesson, Course course) {
       courseService.addLesson(course.getCourseID(), lesson);
    }

    public void editLesson(String lessonID, String title, String content,String courseID) {
        Course course = courseService.getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course not found!");
        }
        for(Lesson lesson : course.getLessons()){
            if(lesson.getLessonID().equals(lessonID)){
                lesson.setTitle(title);
                lesson.setContent(content);
                courseService.saveCourse(course);
                return;
            }
        }
        throw new IllegalArgumentException("Lesson not found!");
    }

    public void deleteLesson(Lesson lesson, String courseID) {
        courseService.removeLesson(courseID,lesson);
    }

    public void markAsCompleted(String lessonID, String courseID) {
        Course course = courseService.getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course not found!");
        }
        for(Lesson lesson : course.getLessons()){
            if(lesson.getLessonID().equals(lessonID)){
                lesson.setCompleted();
                courseService.saveCourse(course);
                return;

            }
        }
        throw new IllegalArgumentException("Lesson not found!");
    }

    public Lesson getLesson(String lessonID, String courseID) {
        Course course = courseService.getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course not found!");
        }
        for(Lesson lesson : course.getLessons()){
            if(lesson.getLessonID().equals(lessonID)){
                return lesson;
            }
        }
        return null;

    }

}
