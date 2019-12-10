package com.damiwawo.BoektQuizt.jms;

import com.damiwawo.BoektQuizt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Service;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class MemberJmsReceiver {
        private MemberService memberService;

        @Autowired public void setMemberService(MemberService memberService){
            this.memberService = memberService;
        }

    @Autowired
    public void configure(DefaultJmsListenerContainerFactory fact){
        fact.setPubSubDomain(true);
    }


    @JmsListener(destination = "MemberQueue")
    public void onMessage(Message msg){
        try{
            if(msg instanceof TextMessage){
                String text= ((TextMessage)msg).getText();
                System.out.println("JMS receiver ontvangt dit:" + text);
            }
        }
        catch(JMSException e ){
            e.printStackTrace();
        }
    }
}
