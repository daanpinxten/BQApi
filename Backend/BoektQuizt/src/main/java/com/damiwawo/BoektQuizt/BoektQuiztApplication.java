package com.damiwawo.BoektQuizt;

import com.damiwawo.BoektQuizt.jms.ExampleSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableJms
public class BoektQuiztApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(BoektQuiztApplication.class,args);
		ExampleSender sender = ctx.getBean(ExampleSender.class);
		//ExampleReceiver receiver = ctx.getBean(ExampleReceiver.class);
		sender.sendHello("Hallo van JMS");



	}

}
