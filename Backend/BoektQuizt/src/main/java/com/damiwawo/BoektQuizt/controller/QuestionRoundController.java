package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.QuestionRound;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.QuestionRoundService;
import com.damiwawo.BoektQuizt.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionRoundController {

    @Autowired
    QuestionRoundService questionRoundService;

    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/questionRound", produces = "application/json")
    ResponseEntity<Iterable<QuestionRound>> getAll() {
        return new ResponseEntity(questionRoundService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/questionRound/{id}", produces = "application/json")
    ResponseEntity<QuestionRound> get(@PathVariable Integer id) {

        Optional<QuestionRound> questionRound = questionRoundService.findById(id);

        if (questionRound.isPresent())
            return new ResponseEntity(questionRound.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("QuestionRound object with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/questionRound", produces = "application/json", consumes = "application/json")
    ResponseEntity<QuestionRound> post(@RequestBody QuestionRound questionRound) {
        return new ResponseEntity(questionRoundService.save(questionRound), HttpStatus.CREATED);
    }

    @PutMapping(value = "/questionRound", produces = "application/json", consumes = "application/json")
    ResponseEntity<QuestionRound> put(@RequestBody QuestionRound questionRound) {
        return new ResponseEntity(questionRoundService.save(questionRound), HttpStatus.OK);
    }

    @DeleteMapping("/questionRound/{id}")
    ResponseEntity delete(@PathVariable Integer id) {

        Optional<QuestionRound> questionRound = questionRoundService.findById(id);

        if (questionRound.isPresent()) {
            questionRoundService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("QuestionRound object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("QuestionRound object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }

}
