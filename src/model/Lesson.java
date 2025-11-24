package model;

public class Lesson {

    private String lessonID;
    private String title;
    private String content;
    private boolean isCompleted = false;
    private int completedCount = 0;
    private double averageScore = 0;
    private int quizAttempts = 0;

    public Lesson(String lessonID, String title, String content) {
        this.lessonID = lessonID;
        this.title = title;
        this.content = content;
    }
    private Quiz quiz;

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    // ============================================================
    // GETTERS & SETTERS
    // ============================================================

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // ============================================================
    // ANALYTICS FIELDS
    // ============================================================

    public int getCompletedCount() {
        return completedCount;
    }

    public void increaseCompletedCount() {
        this.completedCount++;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getQuizAttempts() {
        return quizAttempts;
    }

    // Update average using running formula (no maps)
    public void updateQuizAnalytics(double newScore) {
        quizAttempts++;
        averageScore = ((averageScore * (quizAttempts - 1)) + newScore) / quizAttempts;
    }

    public void setQuizAttempts(int quizAttempts) {
        this.quizAttempts = quizAttempts;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
    }
}
