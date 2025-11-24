package model;

import service.LessonProgress;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private List<Course> enrolledCourses;
    private List<LessonProgress> lessonProgressList = new ArrayList<>();
    public Student(String userId, String name, String email, String password) {
        super(userId,"student", name, email, password);
    }

    public List<LessonProgress> getLessonProgress() {
        return lessonProgressList;
    }

    public LessonProgress getProgressForLesson(String lessonId) {
        for (LessonProgress lp : lessonProgressList) {
            if (lp.getLessonID().equals(lessonId)) {
                return lp;
            }
        }
        return null;
    }

    public boolean hasCompletedLesson(String lessonId) {
        for (LessonProgress lp : lessonProgressList) {
            if (lp.getLessonID().equals(lessonId)) {
                return lp.isCompleted();
            }
        }
        return false;
    }

    public void addOrUpdateProgress(LessonProgress newProgress) {
        for (int i = 0; i < lessonProgressList.size(); i++) {
            LessonProgress existing = lessonProgressList.get(i);
            if (existing.getLessonID().equals(newProgress.getLessonID())) {
                lessonProgressList.set(i, newProgress);
                return;
            }
        }
        lessonProgressList.add(newProgress);
    }

    public void markLessonCompleted(String lessonId) {
        LessonProgress lp = getProgressForLesson(lessonId);
        if (lp == null) {
            lp = new LessonProgress(lessonId,true,0,0);
            lp.setCompleted(true);
            lessonProgressList.add(lp);
        } else {
            lp.setCompleted(true);
        }
    }

    public void recordQuizAttempt(String lessonId, double score) {
        LessonProgress lp = getProgressForLesson(lessonId);
        if (lp == null) {
            lp = new LessonProgress(lessonId,false,0,0);
            lp.setAttempts(1);
            lp.setQuizScore(score);
            lessonProgressList.add(lp);
        } else {
            lp.setAttempts(lp.getAttempts() + 1);
            lp.setQuizScore(score);
        }
    }

    public void setLessonProgressList(List<LessonProgress> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<LessonProgress> getLessonProgressList() {
        return lessonProgressList;
    }
}
