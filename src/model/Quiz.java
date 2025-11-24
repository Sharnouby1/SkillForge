package model;

import java.util.List;
import java.util.ArrayList;

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

            if (isCorrect) score++;

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
