package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends CrudRepository<Member, Integer> {
    Iterable<Member> findByFirstNameAndLastName(String firstName, String lastName);

    Member findMemberById(int memberId);


}
