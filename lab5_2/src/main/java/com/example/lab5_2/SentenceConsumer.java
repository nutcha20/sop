package com.example.lab5_2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SentenceConsumer{
    protected Sentence sentence = new Sentence();
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "GetQueue")
    public Sentence getSentence() {
        return sentence;
    }

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s){
        this.sentence.badSentences.add(s);
        System.out.println("In addBadSentence Method : "+ this.sentence.badSentences);
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s){
        this.sentence.goodSentences.add(s);
        System.out.println("In addGoodSentence Method : "+ this.sentence.goodSentences);
    }
}
