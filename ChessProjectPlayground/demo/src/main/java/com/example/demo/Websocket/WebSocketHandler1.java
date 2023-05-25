package com.example.demo.Websocket;

import com.example.demo.Board;
import com.example.demo.FenstringToBoard;
import com.example.demo.Game;
import com.example.demo.Pieces.Pieces;
import org.springframework.web.socket.*;

public class WebSocketHandler1 implements WebSocketHandler {

    private Game game;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection established: " + session.getId());
        game = new Game();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = message.getPayload().toString();
        System.out.println("Received message: " + msg);

        try{
            System.out.println(msg.length());
            if(msg.length() == 4 ){
                System.out.println("HI");
                game.makeAMove(msg);
                String move = ChessEngine.analyse(game.getBoard().toFenString());
                session.sendMessage(new TextMessage(move));

            }
        }catch (Exception e){
            System.out.println(e);
        }

        session.sendMessage(new TextMessage("Echo: " + msg));
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("WebSocket error: " + session.getId());
    }
}