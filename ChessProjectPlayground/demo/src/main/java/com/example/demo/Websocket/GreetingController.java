package com.example.demo.Websocket;


import com.example.demo.Board;
import com.example.demo.Pieces.Pieces;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        //logic
        Board board = new Board(new Pieces[8][8]);
        return new Greeting("The best move is: " + "!"); // "8/8/4Rp2/5P2/1PP1pkP1/7P/1P1r4/7K b - - 0 40"
    }

}
