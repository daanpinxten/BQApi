package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService extends CrudRepository<Answer, Integer> {
}
