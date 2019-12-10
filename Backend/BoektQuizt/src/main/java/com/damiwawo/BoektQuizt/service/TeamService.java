package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.Member;
import com.damiwawo.BoektQuizt.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TeamService extends CrudRepository<Team, Integer> {
    Team findTeamById(int memberId);
}
