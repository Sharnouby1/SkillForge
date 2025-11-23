package model;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.util.Date;

// Quiz.java

public class Quiz {
    private String quizId;
    private String lessonId;
    private List<Question> questions;
    private int passingScore;
    private int maxAttempts;

    public Quiz(String quizId, String lessonId, int passingScore, int maxAttempts) {
        this.quizId = quizId;
        this.lessonId = lessonId;
        this.questions = new ArrayList<>();
        this.passingScore = passingScore;
        this.maxAttempts = maxAttempts;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public QuizResult evaluateAttempt(List<String> userAnswers) {
        int score = 0;
        List<QuestionResult> questionResults = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = i < userAnswers.size() ? userAnswers.get(i) : "";
            boolean isCorrect = question.isCorrect(userAnswer);

            if (isCorrect) {
                score++;
            }

            questionResults.add(new QuestionResult(question, userAnswer, isCorrect));
        }

        double percentage = (double) score / questions.size() * 100;
        boolean passed = percentage >= passingScore;

        return new QuizResult(quizId, score, questions.size(), percentage, passed, questionResults);
    }

    // Getters
    public String getQuizId() { return quizId; }
    public String getLessonId() { return lessonId; }
    public List<Question> getQuestions() { return questions; }
    public int getPassingScore() { return passingScore; }
    public int getMaxAttempts() { return maxAttempts; }
}

// Question.java
class Question {
    private String questionId;
    private String text;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionId, String text, List<String> options, String correctAnswer) {
        this.questionId = questionId;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }

    // Getters
    public String getQuestionId() { return questionId; }
    public String getText() { return text; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }
}

// QuizResult.java
class QuizResult {
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

    // Getters
    public String getQuizId() { return quizId; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public double getPercentage() { return percentage; }
    public boolean isPassed() { return passed; }
    public Date getAttemptedAt() { return attemptedAt; }
    public List<QuestionResult> getQuestionResults() { return questionResults; }
}

// QuestionResult.java
class QuestionResult {
    private Question question;
    private String userAnswer;
    private boolean correct;

    public QuestionResult(Question question, String userAnswer, boolean correct) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correct = correct;
    }

    // Getters
    public Question getQuestion() { return question; }
    public String getUserAnswer() { return userAnswer; }
    public boolean isCorrect() { return correct; }
}
