// Question.java
package model;
import java.util.List;

public class Question {   // <- public هنا
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

    // getters
    public String getQuestionId() { return questionId; }
    public String getText() { return text; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }
}
