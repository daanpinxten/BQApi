package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.QuestionRound;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface QuestionRoundService extends CrudRepository<QuestionRound, Integer> {
}
