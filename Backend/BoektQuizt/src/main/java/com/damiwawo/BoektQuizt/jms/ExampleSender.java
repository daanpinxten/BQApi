package com.damiwawo.BoektQuizt.jms;

import com.damiwawo.BoektQuizt.model.Member;
import com.damiwawo.BoektQuizt.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ExampleSender {
    private JmsTemplate jmsTemplate;
    private Member member;
    private Team team;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendHello(String text){
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend("MemberQueue",text);
        System.out.println("JMS sender verstuur dit: " + text);
    }
}
