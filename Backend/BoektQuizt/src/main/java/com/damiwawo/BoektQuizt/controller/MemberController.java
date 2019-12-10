package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Member;
import com.damiwawo.BoektQuizt.model.helpers.ServerResponse;
import com.damiwawo.BoektQuizt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping(value = "/member/search", produces = "application/json")
    ResponseEntity<Iterable<Member>> findByQuery(@RequestParam("first") String firstname, @RequestParam("Last") String lastName) {
        return new ResponseEntity(memberService.findByFirstNameAndLastName(firstname, lastName), HttpStatus.OK);
    }

    @GetMapping(value = "/member", produces = "application/json")
    ResponseEntity<Iterable<Member>> getAll() {
        return new ResponseEntity(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/member/{id}", produces = "application/json")
    ResponseEntity<Member> get(@PathVariable Integer id) {

        Optional<Member> member = memberService.findById(id);

        if (member.isPresent())
            return new ResponseEntity(member.get(), HttpStatus.OK);
        else
            return new ResponseEntity(ServerResponse.notFound(String.format("Member object with id (%d) could not be found.", id)), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/member", produces = "application/json", consumes = "application/json")
    ResponseEntity<Member> post(@RequestBody Member member) {
        return new ResponseEntity(memberService.save(member), HttpStatus.CREATED);
    }

    @PutMapping(value = "/member", produces = "application/json", consumes = "application/json")
    ResponseEntity<Member> put(@RequestBody Member member) {
        return new ResponseEntity(memberService.save(member), HttpStatus.OK);
    }

    @DeleteMapping("/member/{id}")
    ResponseEntity delete(@PathVariable Integer id) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            memberService.deleteById(id);
            return new ResponseEntity(ServerResponse.ok(String.format("Member object with id %d has been deleted.", id)), HttpStatus.OK);
        } else
            return new ResponseEntity(ServerResponse.badRequest(String.format("Member object with id %d has not been found.", id)), HttpStatus.BAD_REQUEST);
    }
}
