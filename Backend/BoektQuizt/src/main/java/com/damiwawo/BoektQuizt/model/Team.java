package com.damiwawo.BoektQuizt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    public Team() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;



    //fetch = FetchType.EAGER, mappedBy = "team",
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // !
    private List<Member> members;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quizId")
    @JsonIgnore // !
    private Quiz quiz;


    private byte[] rawImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public byte[] getRawImage() {
        return rawImage;
    }

    public void setRawImage(byte[] rawImage) {
        this.rawImage = rawImage;
    }
}
