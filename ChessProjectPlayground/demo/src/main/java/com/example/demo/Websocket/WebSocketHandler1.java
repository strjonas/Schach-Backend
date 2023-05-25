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
            // move e2e4
            // fen
            // hist
            if (msg.length() > 3) {
                switch(msg.substring(0,4)) {
                    case "move" -> {
                        if(game.makeAMove(msg)){
                            String move = ChessEngine.analyse(game.getBoard().toFenString());
                            game.makeAMove(move);
                            session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                        } else{
                            session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                        }
                    }
                    case "fen "-> {
                        session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                    }
                    case "hist" -> {
                        session.sendMessage(new TextMessage("hist " + String.join(",", game.getBoard().get_history())));
                    }
                    default -> throw new IOException("Not a correct modifier");
                }
            }

            /*if(msg.strip().contains("move") ){
                if(game.makeAMove(msg)){
                    String move = ChessEngine.analyse(game.getBoard().toFenString());
                    game.makeAMove(move);
                    session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                }
                else{
                    session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                }
                
                

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