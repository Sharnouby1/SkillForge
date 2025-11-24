package service;

public class LessonProgress {

    private String lessonID;
    private boolean isCompleted;
    private int attempts;
    private double quizScore;


    public LessonProgress(String lessonID, boolean isCompleted, int attempts, double quizScore) {
        this.lessonID = lessonID;
        this.isCompleted = isCompleted;
        this.attempts = attempts;
        this.quizScore = quizScore;
    }

    public String getLessonID() {
        return lessonID;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public double getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(double quizScore) {
        this.quizScore = quizScore;
    }


}
