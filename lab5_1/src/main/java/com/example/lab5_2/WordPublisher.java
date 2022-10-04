package com.example.lab5_2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class WordPublisher {
    protected Word word = new Word();
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/addbad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String word){
        this.word.badWords.add(word);
        return this.word.badWords;
    }
    @RequestMapping(value = "/delbad/{word}", method = RequestMethod.GET)
    public ArrayList<String> delBadWord(@PathVariable("word") String word){
        this.word.badWords.remove(word);
        return this.word.badWords;
    }
    @RequestMapping(value = "/addgood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String word){
        this.word.goodWords.add(word);
        return this.word.goodWords;
    }
    @RequestMapping(value = "/delgood/{word}", method = RequestMethod.GET)
    public ArrayList<String> delGoodWord(@PathVariable("word") String word){
        this.word.goodWords.remove(word);
        return this.word.goodWords;
    }
    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){
        Object sentence = rabbitTemplate.convertSendAndReceive("Direct","sentence", "");
        return (Sentence) sentence;
    }
    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
    public String proofSentence(@PathVariable("sentence") String sentence){
        boolean good = false;
        boolean bad = false;
        for(int i=0; i < this.word.goodWords.size(); i++ ) {
            if (sentence.contains(this.word.goodWords.get(i))) {
//                System.out.println("In addGoodSentence Method : " + sentence);
                good = true;
                break;
            }
        }
        for(int i=0; i < this.word.badWords.size(); i++ ) {
            if (sentence.contains(this.word.badWords.get(i))) {
//                System.out.println("In addBadSentence Method: " + sentence);
                bad = true;
                break;
            }
        }

        if (good && bad) {
            rabbitTemplate.convertAndSend("Fanout", "",sentence);
            return "Found Good&Bad Word";
        } else if (good) {
            rabbitTemplate.convertAndSend("Direct", "good", sentence);
            return "Found Good Word";
        } else if (bad) {
            rabbitTemplate.convertAndSend("Direct", "bad", sentence);
            return "Found Bad Word";
        } else {
            return "non ka mai mee ka";
        }
    }
}
