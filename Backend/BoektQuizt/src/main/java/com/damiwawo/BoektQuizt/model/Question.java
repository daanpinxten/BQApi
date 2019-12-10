package com.damiwawo.BoektQuizt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // !
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionRoundId")
    @JsonIgnore // !
    private QuestionRound questionRound;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public QuestionRound getQuestionRound() {
        return questionRound;
    }

    public void setQuestionRound(QuestionRound questionRound) {
        this.questionRound = questionRound;
    }
}
