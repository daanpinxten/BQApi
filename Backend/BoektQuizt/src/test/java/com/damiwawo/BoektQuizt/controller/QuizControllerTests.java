package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Quiz;
import com.damiwawo.BoektQuizt.service.QuizService;
import com.damiwawo.BoektQuizt.service.TeamService;
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
public class QuizControllerTests {

    @InjectMocks
    private QuizController controller;

    @Mock
    private QuizService quizService;

    @Mock
    private TeamService teamService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenQuizDoesNotExist_whenQuizInfoIsRetrieved_then404IsReceived() {

        Mockito.when(quizService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Quiz> quiz = controller.get(id);

        assertEquals(quiz.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenQuizDoesNotExist_whenQuizIsDeleted_then400IsReceived() {

        Mockito.when(quizService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Quiz> quiz = controller.delete(id);

        assertEquals(quiz.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenQuizDoesExist_whenQuizIsDeleted_then200IsReceived() {

        Quiz quiz = new Quiz();
        quiz.setId(id);

        Mockito.when(quizService.findById(id)).thenReturn(Optional.ofNullable(quiz));

        ResponseEntity<Quiz> receivedQuiz = controller.delete(id);

        assertEquals(receivedQuiz.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(quizService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<Quiz>> receivedQuizzes = controller.getAll();

        assertEquals(receivedQuizzes.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfQuizsIsReturned() {

        List<Quiz> quizzes = new ArrayList<>();

        Mockito.when(quizService.findAll()).thenReturn(quizzes);
        ResponseEntity<Iterable<Quiz>> receivedQuizzes = controller.getAll();

        assertEquals(receivedQuizzes.getBody(), quizzes);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() throws ValidationException {

        Quiz quiz = new Quiz();
        quiz.setId(id);

        Mockito.when(quizService.save(quiz)).thenReturn(quiz);

        ResponseEntity<Quiz> receivedQuizzes = controller.post(quiz);

        assertEquals(receivedQuizzes.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenAQuizIsReceived() throws ValidationException {

        Quiz quiz = new Quiz();
        quiz.setId(id);

        Mockito.when(quizService.save(quiz)).thenReturn(quiz);

        ResponseEntity<Quiz> receivedQuiz = controller.post(quiz);

        assertEquals(receivedQuiz.getBody(), quiz);
    }

    @Test
    public void whenUpdateIsCalled_thenAQuizIsReceived() {

        Quiz quiz = new Quiz();
        quiz.setId(id);

        Mockito.lenient().when(quizService.save(quiz)).thenReturn(quiz);

        id =+ 1;
        quiz.setId(id);

        ResponseEntity<Quiz> receivedQuizzes = controller.put(quiz);

        assertEquals(receivedQuizzes.getBody(), quiz);
        assertEquals(receivedQuizzes.getBody().getId(), quiz.getId());
    }
}
