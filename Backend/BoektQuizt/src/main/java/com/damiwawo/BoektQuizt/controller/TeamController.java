package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Team;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.MemberService;
import com.damiwawo.BoektQuizt.jms.ExampleSender;
import com.damiwawo.BoektQuizt.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    MemberService memberService;

    @GetMapping("/team")
    ResponseEntity<Iterable<Team>> getAll() {
        return new ResponseEntity(teamService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/team", produces = "application/json", consumes = "application/json")
    ResponseEntity<Team> post(@RequestBody Team team) {

        return new ResponseEntity(teamService.save(team), HttpStatus.CREATED);
    }

    @PutMapping(value = "/team", produces = "application/json", consumes = "application/json")
    ResponseEntity<Team> put(@RequestBody Team team) {
        return new ResponseEntity(teamService.save(team), HttpStatus.OK);
    }


    @GetMapping(value = "/team/{id}", produces = "application/json")
    ResponseEntity<Team> get(@PathVariable Integer id) {

        Optional<Team> team = teamService.findById(id);

        ExampleSender sender;

        if (team.isPresent())
            return new ResponseEntity(team.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Team object with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/team/{id}")
    ResponseEntity delete(@PathVariable Integer id) {
        Optional<Team> team = teamService.findById(id);

        if (team.isPresent()) {
            teamService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("Team object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("Team object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/team/{teamId}/{memberId}")
    ResponseEntity<Team> updateTeamList(@PathVariable Integer teamId, @PathVariable Integer memberId) {
        Team toBeUpdatedTeam = teamService.findTeamById(teamId);
        toBeUpdatedTeam.getMembers().add(memberService.findById(memberId).get());
        //  List<Member> teamMembers = toBeUpdatedTeam.getMembers();

//        if (teamMembers == null) {
//            teamMembers = new ArrayList<>();
//        }
//        Optional<Member> toBeAddedMember = memberService.findById(memberId);
//
//        if (toBeAddedMember.isPresent()){
//            teamMembers.add(toBeAddedMember.get());
//        } else{
//            //fout
//        }
//
//        List<Member> newList = new ArrayList<>();
//
//        for(Member member : teamMembers) {
//            newList.add(member);
//        }
//        toBeUpdatedTeam.setMembers(newList);

        return new ResponseEntity(teamService.save(toBeUpdatedTeam), HttpStatus.OK);
    }

}






