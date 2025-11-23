package model;

import database.JsonDatabaseManager;
import service.LessonProgress;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsManager {

    private final JsonDatabaseManager db;

    public AnalyticsManager(JsonDatabaseManager db) {
        this.db = db;
    }

    // ======================================================
    // 1) RECORD LESSON COMPLETION (LISTS ONLY)
    // ======================================================
    public void recordLessonCompletion(String studentId, String courseId, String lessonId) {

        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        Lesson lesson = findLesson(course, lessonId);

        if (student == null || course == null || lesson == null) return;

        // Ensure student has a progress list
        List<LessonProgress> progressList = student.getLessonProgress();
        if (progressList == null) {
            progressList = new ArrayList<>();
            student.setLessonProgress(progressList);
        }

        LessonProgress progress = findLessonProgress(progressList, lessonId);

        if (progress == null) {
            progress = new LessonProgress(lessonId, true, 0, 0);
            progressList.add(progress);
        } else {
            progress.setCompleted(true);
        }

        // Update lesson analytics (count)
        lesson.setCompletedCount(lesson.getCompletedCount() + 1);

    }

    // ======================================================
    // 2) RECORD QUIZ RESULT
    // ======================================================
    public void recordQuizResult(String studentId, String courseId, String lessonId, double score) {

        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        Lesson lesson = findLesson(course, lessonId);

        if (student == null || course == null || lesson == null) return;

        List<LessonProgress> progressList = student.getLessonProgress();
        if (progressList == null) {
            progressList = new ArrayList<>();
            student.setLessonProgress(progressList);
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

        db.saveUsers();
        db.saveCourses();
    }

    // ======================================================
    // 3) COURSE STATISTICS
    // ======================================================
    public CourseAnalytics getCourseAnalytics(String courseId) {

        Course course = findCourse(courseId);
        if (course == null) return null;

        int totalLessons = course.getLessons().size();
        int totalCompletions = 0;

        for (Lesson lesson : course.getLessons()) {
            totalCompletions += lesson.getCompletedCount();
        }

        return new CourseAnalytics(courseId, totalLessons, totalCompletions);
    }

    // ======================================================
    // HELPERS
    // ======================================================
    private Student findStudent(String id) {
        for (User u : db.viewUsers()) {
            if (u instanceof Student && u.getUserId().equals(id)) {
                return (Student) u;
            }
        }
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
