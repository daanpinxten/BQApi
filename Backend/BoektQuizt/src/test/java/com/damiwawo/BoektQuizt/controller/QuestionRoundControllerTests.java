package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.QuestionRound;
import com.damiwawo.BoektQuizt.service.QuestionRoundService;
import com.damiwawo.BoektQuizt.service.QuestionService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRoundControllerTests {

    @InjectMocks
    private QuestionRoundController controller;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionRoundService questionRoundService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenQuestionRoundDoesNotExist_whenQuestionRoundInfoIsRetrieved_then404IsReceived() {

        Mockito.when(questionRoundService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<QuestionRound> questionRound = controller.get(id);

        assertEquals(questionRound.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenQuestionRoundDoesNotExist_whenQuestionRoundIsDeleted_then400IsReceived() {

        Mockito.when(questionRoundService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<QuestionRound> questionRound = controller.delete(id);

        assertEquals(questionRound.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenQuestionRoundDoesExist_whenQuestionRoundIsDeleted_then200IsReceived() {

        QuestionRound questionRound = new QuestionRound();
        questionRound.setId(id);

        Mockito.when(questionRoundService.findById(id)).thenReturn(Optional.ofNullable(questionRound));

        ResponseEntity<QuestionRound> receivedQuestionRound = controller.delete(id);

        assertEquals(receivedQuestionRound.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(questionRoundService.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<Iterable<QuestionRound>> receivedQuestionRounds = controller.getAll();

        assertEquals(receivedQuestionRounds.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfQuestionRoundsIsReturned() {

        List<QuestionRound> questionRounds = new ArrayList<>();

        Mockito.when(questionRoundService.findAll()).thenReturn(questionRounds);
        ResponseEntity<Iterable<QuestionRound>> receivedQuestions = controller.getAll();

        assertEquals(receivedQuestions.getBody(), questionRounds);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() throws ValidationException {

        QuestionRound questionRound = new QuestionRound();
        questionRound.setId(id);

        Mockito.when(questionRoundService.save(questionRound)).thenReturn(questionRound);

        ResponseEntity<QuestionRound> receivedQuestions = controller.post(questionRound);

        assertEquals(receivedQuestions.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenAQuestionRoundIsReceived() throws ValidationException {

        QuestionRound questionRound = new QuestionRound();
        questionRound.setId(id);

        Mockito.when(questionRoundService.save(questionRound)).thenReturn(questionRound);

        ResponseEntity<QuestionRound> receivedQuestionRound = controller.post(questionRound);

        assertEquals(receivedQuestionRound.getBody(), questionRound);
    }

    @Test
    public void whenUpdateIsCalled_thenAQuestionRoundIsReceived() {

        QuestionRound questionRound = new QuestionRound();
        questionRound.setId(id);

        Mockito.lenient().when(questionRoundService.save(questionRound)).thenReturn(questionRound);

        id =+ 1;
        questionRound.setId(id);

        ResponseEntity<QuestionRound> receivedQuestionRound = controller.put(questionRound);

        assertEquals(receivedQuestionRound.getBody(), questionRound);
        assertEquals(receivedQuestionRound.getBody().getId(), questionRound.getId());
    }
}