package ru.ruscalworld.bortexel4j.models.forms;

public class Question {
    private final String question;
    private String answer;

    public Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
