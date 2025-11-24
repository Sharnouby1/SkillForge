package service;

import model.Course;
import model.Lesson;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseAnalytics {

    private Course course;

    public CourseAnalytics(Course course) {
        this.course = course;
    }

    // -------------------------------------------------------------------------
    // 1️⃣ QUIZ AVERAGES PER LESSON  (returns list aligned with course.getLessons())
    // -------------------------------------------------------------------------
    public List<Double> getQuizAveragesPerLesson() {

        List<Double> averages = new ArrayList<>();

        for (Lesson lesson : course.getLessons()) {

            double total = 0;
            int count = 0;

            for (Student s : course.getStudents()) {

                LessonProgress lp = (LessonProgress) s.getLessonProgress();

                if (lp != null && lp.getAttempts() > 0) {
                    total += lp.getQuizScore();
                    count++;
                }
            }

            averages.add(count == 0 ? 0.0 : total / count);
        }

        return averages;
    }

    // -------------------------------------------------------------------------
    // 2️⃣ COMPLETION PERCENTAGES FOR EACH LESSON
    // -------------------------------------------------------------------------
    public List<Double> getCompletionPercentages() {

        List<Double> percentages = new ArrayList<>();

        int studentCount = course.getStudents().size();

        for (Lesson lesson : course.getLessons()) {

            int completed = 0;

            for (Student s : course.getStudents()) {
                LessonProgress lp = (LessonProgress) s.getLessonProgress();
                if (lp != null && lp.isCompleted()) {
                    completed++;
                }
            }

            double percentage = (studentCount == 0) ? 0.0 : (completed * 100.0 / studentCount);
            percentages.add(percentage);
        }

        return percentages;
    }

    // -------------------------------------------------------------------------
    // 3️⃣ CLASS AVERAGE TREND (one point per lesson in order)
    // -------------------------------------------------------------------------
    public List<Double> getClassAverageTrend() {

        List<Double> trend = new ArrayList<>();

        for (Lesson lesson : course.getLessons()) {

            double total = 0;
            int count = 0;

            for (Student s : course.getStudents()) {
                LessonProgress lp = (LessonProgress) s.getLessonProgress();
                if (lp != null) {
                    total += lp.getQuizScore();
                    count++;
                }
            }

            double avg = (count == 0) ? 0.0 : total / count;
            trend.add(avg);
        }

        return trend;
    }

    // -------------------------------------------------------------------------
    // 4️⃣ GET LESSON TITLES AS A LIST (useful for charts)
    // -------------------------------------------------------------------------
    public List<String> getLessonTitles() {
        List<String> titles = new ArrayList<>();
        for (Lesson l : course.getLessons()) {
            titles.add(l.getTitle());
        }
        return titles;
    }
}
