package com.example.demo.Websocket;

import com.example.demo.Board;
import com.example.demo.FenstringToBoard;
import com.example.demo.Game;
import com.example.demo.Pieces.Pieces;
import org.springframework.web.socket.*;

import java.util.Objects;

public class WebSocketHandler1 implements WebSocketHandler {

    private Game game;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection established: " + session.getId());
        game = new Game();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        String msg = message.getPayload().toString();
        /*System.out.println("Received message: " + msg);
        System.out.println(msg.substring(0,4));
        System.out.println("Received message: " + msg);
         */

        try{
            session.sendMessage(new TextMessage("Echo: " + msg));
            // move e2e4
            // fen
            // hist
            System.out.println(msg);
            switch (msg.substring(0, 4)) {

                case "move" -> {
                    if (game.makeAMove(msg)) {
                        String move ="move " + ChessEngine.analyse(game.getBoard().toFenString());
                        System.out.println(move);
                        if (move.contains("none")) {
                            session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                            session.sendMessage(new TextMessage("end  " + (game.getBoard().isWhitesMove() ? "b" : "w")));
                        } else {
                            game.makeAMove(move);
                            if (ChessEngine.analyse(game.getBoard().toFenString()).contains("none")) {
                                session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                                session.sendMessage(new TextMessage("end  " + (game.getBoard().isWhitesMove() ? "b" : "w")));
                            } else {
                                System.out.println(game.getBoard().toFenString());
                                session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                            }
                        }

                    } else {
                        session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                    }
                }
                case "fen " -> {
                    session.sendMessage(new TextMessage("fen  " + game.getBoard().toFenString()));
                }
                case "hist" -> {
                    session.sendMessage(new TextMessage("hist " + String.join(",", game.getBoard().get_history())));
                }
                default -> session.sendMessage(new TextMessage("Echo: " + msg));
            }

        }catch (Exception e){
            System.out.println(e);
        }


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