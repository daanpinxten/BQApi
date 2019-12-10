package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.Quiz;
import com.damiwawo.BoektQuizt.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface QuizService extends CrudRepository<Quiz, Integer> {
   // Quiz findTeamsByQuizId(int memberId);
}
