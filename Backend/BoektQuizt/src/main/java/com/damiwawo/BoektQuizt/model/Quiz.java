package com.damiwawo.BoektQuizt.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quizName;
    private int questionRoundId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // !
    private List<QuestionRound> questionRounds;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // !
    private List<Team> teams;



    private byte[] rawImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getQuestionRoundId() {
        return questionRoundId;
    }

    public void setQuestionRoundId(int questionRoundId) {
        this.questionRoundId = questionRoundId;
    }

    public List<QuestionRound> getQuestionRounds() {
        return questionRounds;
    }

    public void setQuestionRounds(List<QuestionRound> questionRounds) {
        this.questionRounds = questionRounds;
    }

    public byte[] getRawImage() {
        return rawImage;
    }

    public void setRawImage(byte[] rawImage) {
        this.rawImage = rawImage;
    }
}
