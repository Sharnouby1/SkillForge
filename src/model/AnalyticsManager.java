package model;

import database.JsonDatabaseManager;
import service.CourseAnalytics;
import service.LessonProgress;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsManager {

    private final JsonDatabaseManager db;

    public AnalyticsManager(JsonDatabaseManager db) {
        this.db = db;
    }

    public void recordLessonCompletion(String studentId, String courseId, String lessonId) {

        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        Lesson lesson = findLesson(course, lessonId);

        if (student == null || course == null || lesson == null) return;

        List<LessonProgress> progressList = student.getLessonProgress();
        if (progressList == null) {
            progressList = new ArrayList<>();
            student.setLessonProgressList(progressList);
        }

        LessonProgress progress = findLessonProgress(progressList, lessonId);

        if (progress == null) {
            progress = new LessonProgress(lessonId, true, 0, 0);
            progressList.add(progress);
        } else {
            progress.setCompleted(true);
        }

        lesson.setCompletedCount(lesson.getCompletedCount() + 1);

    }

    public void recordQuizResult(String studentId, String courseId, String lessonId, double score) {

        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        Lesson lesson = findLesson(course, lessonId);

        if (student == null || course == null || lesson == null) return;

        List<LessonProgress> progressList = student.getLessonProgress();
        if (progressList == null) {
            progressList = new ArrayList<>();
            student.setLessonProgressList(progressList);
        }

        LessonProgress progress = findLessonProgress(progressList, lessonId);

        if (progress == null) {
            progress = new LessonProgress(lessonId, false, 1, score);
            progressList.add(progress);
        } else {
            progress.setAttempts(progress.getAttempts() + 1);
            progress.setQuizScore(score);
        }

        // Update average score analytics
        int attempts = lesson.getQuizAttempts();
        double avg = lesson.getAverageScore();

        double newAvg = ((avg * attempts) + score) / (attempts + 1);

        lesson.setAverageScore(newAvg);
        lesson.setQuizAttempts(attempts + 1);

        db.addUser(student);
        db.editCourse(course);
    }

    public CourseAnalytics getCourseAnalytics(String courseId) {

        Course course = findCourse(courseId);
        if (course == null) return null;

        int totalLessons = course.getLessons().size();
        int totalCompletions = 0;

        for (Lesson lesson : course.getLessons()) {
            totalCompletions += lesson.getCompletedCount();
        }

        return new CourseAnalytics(course);
    }

    private Student findStudent(String id) {

        return null;
    }

    private Course findCourse(String id) {
        for (Course c : db.viewCourses()) {
            if (c.getCourseID().equals(id)) return c;
        }
        return null;
    }

    private Lesson findLesson(Course course, String lessonId) {
        for (Lesson l : course.getLessons()) {
            if (l.getLessonID().equals(lessonId)) return l;
        }
        return null;
    }

    private LessonProgress findLessonProgress(List<LessonProgress> list, String lessonId) {
        for (LessonProgress lp : list) {
            if (lp.getLessonID().equals(lessonId)) return lp;
        }
        return null;
    }
}
