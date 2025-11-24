package model;

public class QuestionResult {
    private Question question;
    private String userAnswer;
    private boolean correct;

    public QuestionResult(Question question, String userAnswer, boolean correct) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correct = correct;
    }
    //
    public Question getQuestion() { return question; }
    public String getUserAnswer() { return userAnswer; }
    public boolean isCorrect() { return correct; }
}