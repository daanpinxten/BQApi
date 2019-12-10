package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Question;
import com.damiwawo.BoektQuizt.service.AnswerService;
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
public class QuestionControllerTests {

    @InjectMocks
    private QuestionController controller;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenQuestionDoesNotExist_whenQuestionInfoIsRetrieved_then404IsReceived() {

        Mockito.when(questionService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Question> question = controller.get(id);

        assertEquals(question.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenQuestionDoesNotExist_whenQuestionIsDeleted_then400IsReceived() {

        Mockito.when(questionService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Question> question = controller.delete(id);

        assertEquals(question.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenQuestionDoesExist_whenQuestionIsDeleted_then200IsReceived() {

        Question question = new Question();
        question.setId(id);

        Mockito.when(questionService.findById(id)).thenReturn(Optional.ofNullable(question));

        ResponseEntity<Question> receivedQuestion = controller.delete(id);

        assertEquals(receivedQuestion.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(questionService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<Question>> receivedQuestions = controller.getAll();

        assertEquals(receivedQuestions.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfQuestionsIsReturned() {

        List<Question> questions = new ArrayList<>();

        Mockito.when(questionService.findAll()).thenReturn(questions);
        ResponseEntity<Iterable<Question>> receivedQuestions = controller.getAll();

        assertEquals(receivedQuestions.getBody(), questions);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() throws ValidationException {

        Question question = new Question();
        question.setId(id);

        question.setAnswers(new ArrayList<>());

        Mockito.when(questionService.save(question)).thenReturn(question);

        ResponseEntity<Question> receivedQuestions = controller.post(question);

        assertEquals(receivedQuestions.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenAQuestionIsReceived() throws ValidationException {

        Question question = new Question();
        question.setId(id);

        question.setAnswers(new ArrayList<>());

        Mockito.when(questionService.save(question)).thenReturn(question);

        ResponseEntity<Question> receivedQuestion = controller.post(question);

        assertEquals(receivedQuestion.getBody(), question);
    }

    @Test
    public void whenUpdateIsCalled_thenAQuestionIsReceived() {

        Question question = new Question();
        question.setId(id);

        Mockito.lenient().when(questionService.save(question)).thenReturn(question);

        id =+ 1;
        question.setId(id);

        ResponseEntity<Question> receivedQuestion = controller.put(question);

        assertEquals(receivedQuestion.getBody(), question);
        assertEquals(receivedQuestion.getBody().getId(), question.getId());
    }
}
