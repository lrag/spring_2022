package com.curso;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receptor {

    @JmsListener(destination = "cola1") 
    public void receiveMessage1(String message) {
        System.out.println("Recibido 1<" + message + ">");
    }

    @JmsListener(destination = "cola2") 
    public void receiveMessage2(String message) {
    	System.out.println("Recibido 2<" + message + ">");
    }
    
}