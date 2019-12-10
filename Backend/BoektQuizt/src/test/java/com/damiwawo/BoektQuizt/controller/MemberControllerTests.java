package com.damiwawo.BoektQuizt.controller;

import com.damiwawo.BoektQuizt.model.Member;
import com.damiwawo.BoektQuizt.service.MemberService;
import org.junit.Test;
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
public class MemberControllerTests {

    @InjectMocks
    private MemberController controller;

    @Mock
    private MemberService memberService;

    private int id;

    @BeforeEach
    public void SetUp() {
        id = new Random().nextInt();
    }

    @Test
    public void givenMemberDoesNotExist_whenMemberInfoIsRetrieved_then404IsReceived() {

        Mockito.when(memberService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Member> member = controller.get(id);

        assertEquals(member.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenMemberDoesNotExist_whenMemberIsDeleted_then400IsReceived() {

        Mockito.when(memberService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Member> member = controller.delete(id);

        assertEquals(member.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenMemberDoesExist_whenMemberIsDeleted_then200IsReceived() {

        Member member = new Member();
        member.setId(id);

        Mockito.when(memberService.findById(id)).thenReturn(Optional.ofNullable(member));

        ResponseEntity<Member> receivedMember = controller.delete(id);

        assertEquals(receivedMember.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_then200IsReceived() {

        Mockito.when(memberService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<Iterable<Member>> member = controller.getAll();

        assertEquals(member.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenGetAllIsCalled_thenListOfMembersIsReturned() {

        List<Member> members = new ArrayList<>();

        Mockito.when(memberService.findAll()).thenReturn(members);
        ResponseEntity<Iterable<Member>> receivedMembers = controller.getAll();

        assertEquals(receivedMembers.getBody(), members);
    }

    @Test
    public void whenAddIsCalled_then201IsReceived() {

        Member member = new Member();
        member.setId(id);

        Mockito.when(memberService.save(member)).thenReturn(member);

        ResponseEntity<Member> receivedMembers = controller.post(member);

        assertEquals(receivedMembers.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void whenAddIsCalled_thenAMemberIsReceived() {

        Member member = new Member();
        member.setId(id);

        Mockito.when(memberService.save(member)).thenReturn(member);

        ResponseEntity<Member> receivedMember = controller.post(member);

        assertEquals(receivedMember.getBody(), member);
    }

    @Test
    public void whenUpdateIsCalled_thenAMemberIsReceived() {

        Member member = new Member();
        member.setId(id);

        Mockito.when(memberService.save(member)).thenReturn(member);

        id =+ 1;
        member.setId(id);

        ResponseEntity<Member> receivedMember = controller.put(member);

        assertEquals(receivedMember.getBody(), member);
        assertEquals(receivedMember.getBody().getId(), member.getId());
    }
}
