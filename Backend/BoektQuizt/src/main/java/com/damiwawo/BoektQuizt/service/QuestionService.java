package com.damiwawo.BoektQuizt.service;

import com.damiwawo.BoektQuizt.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public interface QuestionService extends CrudRepository<Question, Integer> {
}
