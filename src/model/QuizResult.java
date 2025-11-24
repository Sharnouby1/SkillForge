// QuizResult.java
package model;
import java.util.Date;
import java.util.List;

public class QuizResult {   // <- public هنا
    private String quizId;
    private int score;
    private int totalQuestions;
    private double percentage;
    private boolean passed;
    private Date attemptedAt;
    private List<QuestionResult> questionResults;

    public QuizResult(String quizId, int score, int totalQuestions, double percentage, boolean passed, List<QuestionResult> questionResults) {
        this.quizId = quizId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.passed = passed;
        this.attemptedAt = new Date();
        this.questionResults = questionResults;
    }

    // getters
    public String getQuizId() { return quizId; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public double getPercentage() { return percentage; }
    public boolean isPassed() { return passed; }
    public Date getAttemptedAt() { return attemptedAt; }
    public List<QuestionResult> getQuestionResults() { return questionResults; }
}
