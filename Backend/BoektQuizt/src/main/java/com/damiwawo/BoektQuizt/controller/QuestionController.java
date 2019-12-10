package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Answer;
import com.damiwawo.BoektQuizt.model.Question;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.AnswerService;
import com.damiwawo.BoektQuizt.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping(value = "/question", produces = "application/json")
    ResponseEntity<Iterable<Question>> getAll() {
        return new ResponseEntity(questionService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/question/{id}", produces = "application/json")
    ResponseEntity<Question> get(@PathVariable Integer id) {

        Optional<Question> question = questionService.findById(id);

        if (question.isPresent())
            return new ResponseEntity(question.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Question object with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/question", produces = "application/json", consumes = "application/json")
    ResponseEntity<Question> post(@RequestBody Question question) throws ValidationException {

        List<Answer> answers = question.getAnswers();

        if(answers != null){
            for (int i = 0; i < answers.size(); i++) {
                answerService.save(answers.get(i));
            }
        }

        return new ResponseEntity(questionService.save(question), HttpStatus.CREATED);
    }

    @PutMapping(value = "/question", produces = "application/json", consumes = "application/json")
    ResponseEntity<Question> put(@RequestBody Question question) {
        if (questionService.findById(question.getId()).isPresent()) {
            return new ResponseEntity(questionService.save(question), HttpStatus.OK);
        } else
            return new ResponseEntity(question, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/question/{id}")
    ResponseEntity delete(@PathVariable Integer id) {

        Optional<Question> question = questionService.findById(id);

        if (question.isPresent()) {
            questionService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("Question object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("Question object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<String> exeptionHandler(ValidationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
