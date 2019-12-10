package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Team;
import com.damiwawo.BoektQuizt.service.MemberService;
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
public class TeamControllerTests {

    @InjectMocks
    private TeamController controller;

    @Mock
    private TeamService teamService;

    @Mock
    private MemberService memberService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenTeamDoesNotExist_whenTeamInfoIsRetrieved_then404IsReceived() {

        Mockito.when(teamService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Team> team = controller.get(id);

        assertEquals(team.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenTeamDoesNotExist_whenTeamIsDeleted_then400IsReceived() {

        Mockito.when(teamService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Team> team = controller.delete(id);

        assertEquals(team.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenTeamDoesExist_whenTeamIsDeleted_then200IsReceived() {

        Team team = new Team();
        team.setId(id);

        Mockito.when(teamService.findById(id)).thenReturn(Optional.ofNullable(team));

        ResponseEntity<Team> receivedTeam = controller.delete(id);

        assertEquals(receivedTeam.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(teamService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<Team>> receivedTeams = controller.getAll();

        assertEquals(receivedTeams.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfTeamsIsReturned() {

        List<Team> teams = new ArrayList<>();

        Mockito.when(teamService.findAll()).thenReturn(teams);
        ResponseEntity<Iterable<Team>> receivedTeams = controller.getAll();

        assertEquals(receivedTeams.getBody(), teams);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() throws ValidationException {

        Team team = new Team();
        team.setId(id);

        Mockito.when(teamService.save(team)).thenReturn(team);

        ResponseEntity<Team> receivedTeam = controller.post(team);

        assertEquals(receivedTeam.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenATeamIsReceived() throws ValidationException {

        Team team = new Team();
        team.setId(id);

        Mockito.when(teamService.save(team)).thenReturn(team);

        ResponseEntity<Team> receivedTeam = controller.post(team);

        assertEquals(receivedTeam.getBody(), team);
    }

    @Test
    public void whenUpdateIsCalled_thenATeamIsReceived() {

        Team team = new Team();
        team.setId(id);

        Mockito.lenient().when(teamService.save(team)).thenReturn(team);

        id =+ 1;
        team.setId(id);

        ResponseEntity<Team> receivedTeam = controller.put(team);

        assertEquals(receivedTeam.getBody(), team);
        assertEquals(receivedTeam.getBody().getId(), team.getId());
    }
}
