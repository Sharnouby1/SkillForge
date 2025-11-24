package model;

public class Lesson {


    private String lessonID;
    private String title;
    private String content;
    private boolean isCompleted;


    public Lesson(String lessonID, String title, String content) {
        this.lessonID = lessonID;
        this.title = title;
        this.content = content;
        this.isCompleted = false;
    }
    private Quiz quiz;

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }
}

