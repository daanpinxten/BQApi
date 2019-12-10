package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Quiz;
import com.damiwawo.BoektQuizt.model.Team;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.QuizService;
import com.damiwawo.BoektQuizt.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/quiz", produces = "application/json")
    ResponseEntity<Iterable<Quiz>> getAll() {
        return new ResponseEntity(quizService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/quiz/{id}", produces = "application/json")
    ResponseEntity<Quiz> get(@PathVariable Integer id) {

        Optional<Quiz> quiz = quizService.findById(id);

        if (quiz.isPresent())
            return new ResponseEntity(quiz.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Quiz object with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }
/*
    @GetMapping(value ="/quiz/{quizId}/teams", produces = "application/json")
    ResponseEntity<Quiz> getTeamNamesFromQuiz(@PathVariable Integer quizId){

        Iterable<Team> team = quizService.findTeamsByQuizId(quizId);
        if(team != null)
            return new ResponseEntity(team, HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Quiz object with id (%d) could not be found.", quizId)), HttpStatus.NOT_FOUND);
    }
    */


    @PostMapping(value = "/quiz", produces = "application/json", consumes = "application/json")
    ResponseEntity<Quiz> post(@RequestBody Quiz quiz) {
        return new ResponseEntity(quizService.save(quiz), HttpStatus.CREATED);
    }

    @PutMapping(value = "/quiz", produces = "application/json", consumes = "application/json")
    ResponseEntity<Quiz> put(@RequestBody Quiz quiz) {
        return new ResponseEntity(quizService.save(quiz), HttpStatus.OK);
    }


    @DeleteMapping("/quiz/{id}")
    ResponseEntity delete(@PathVariable Integer id) {

        Optional<Quiz> quiz = quizService.findById(id);

        if (quiz.isPresent()) {
            quizService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("Quiz object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("Quiz object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }

}
