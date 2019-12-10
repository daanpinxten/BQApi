package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Answer;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping(value = "/answer/{id}", produces = "application/json")
    
    ResponseEntity<Answer> get(@PathVariable Integer id) {

        Optional<Answer> answer = answerService.findById(id);

        if (answer.isPresent())
            return new ResponseEntity(answer.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Answer with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/answer", produces = "application/json")
    ResponseEntity<Iterable<Answer>> getAll() {
        return new ResponseEntity(answerService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/answer", produces = "application/json", consumes = "application/json")
    ResponseEntity<Answer> post(@RequestBody Answer answer) {
        return new ResponseEntity(answerService.save(answer), HttpStatus.CREATED);
    }

    @PutMapping(value = "/answer", produces = "application/json", consumes = "application/json")
    ResponseEntity<Answer> put(@RequestBody Answer answer) {
        return new ResponseEntity(answerService.save(answer), HttpStatus.OK);
    }

    @DeleteMapping("/answer/{id}")
    ResponseEntity delete(@PathVariable Integer id) {

        Optional<Answer> answer = answerService.findById(id);

        if (answer.isPresent()) {
            answerService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("Answer object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("Answer object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }

}
