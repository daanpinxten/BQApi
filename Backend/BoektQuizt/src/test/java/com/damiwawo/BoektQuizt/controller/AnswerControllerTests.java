package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Answer;
import com.damiwawo.BoektQuizt.service.AnswerService;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AnswerControllerTests {

    @InjectMocks
    private AnswerController controller;

    @Mock
    private AnswerService answerService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenAnswerDoesNotExist_whenAnswerInfoIsRetrieved_then404IsReceived() {

        Mockito.when(answerService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Answer> answer = controller.get(id);

        assertEquals(answer.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenAnswerDoesNotExist_whenAnswerIsDeleted_then400IsReceived() {

        Mockito.when(answerService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Answer> answer = controller.delete(id);

        assertEquals(answer.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenAnswerDoesExist_whenAnswerIsDeleted_then200IsReceived() {

        Answer answer = new Answer();
        answer.setId(id);

        Mockito.when(answerService.findById(id)).thenReturn(Optional.ofNullable(answer));

        ResponseEntity<Answer> receivedAnswer = controller.delete(id);

        assertEquals(receivedAnswer.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(answerService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<Answer>> answer = controller.getAll();

        assertEquals(answer.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfAnswersIsReturned() {

        List<Answer> answers = new ArrayList<>();

        Mockito.when(answerService.findAll()).thenReturn(answers);
        ResponseEntity<Iterable<Answer>> answer = controller.getAll();

        assertEquals(answer.getBody(), answers);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() {

        Answer answer = new Answer();
        answer.setId(id);

        Mockito.when(answerService.save(answer)).thenReturn(answer);

        ResponseEntity<Answer> receivedAnswer = controller.post(answer);

        assertEquals(receivedAnswer.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenAnAnswerIsReceived() {

        Answer answer = new Answer();
        answer.setId(id);

        Mockito.when(answerService.save(answer)).thenReturn(answer);

        ResponseEntity<Answer> receivedAnswer = controller.post(answer);

        assertEquals(receivedAnswer.getBody(), answer);
    }

    @Test
    public void whenUpdateIsCalled_thenAnAnswerIsReceived() {

        Answer answer = new Answer();
        answer.setId(id);

        Mockito.when(answerService.save(answer)).thenReturn(answer);

        id =+ 1;
        answer.setId(id);

        ResponseEntity<Answer> receivedAnswer = controller.put(answer);

        assertEquals(receivedAnswer.getBody(), answer);
        assertEquals(receivedAnswer.getBody().getId(), answer.getId());
    }
}
