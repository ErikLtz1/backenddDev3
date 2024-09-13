package com.backenddev3.backenddev3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backenddev3.backenddev3.models.Bullet;
import com.backenddev3.backenddev3.models.Player;

@Controller
public class StompController {

    private List<Player> playerList = new ArrayList<>();
    private Map<String, Boolean> playersReadyMap = new HashMap<>();
    private List<String> playerFinalScores = new ArrayList<>();
    
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/new-player")
    @SendTo("/destroy/player-registration")
    public List<Player> newPlayer(String username) {

        if (playerList.size() == 4) {
            return playerList;
        }

        int playerNumber = playerList.size() + 1;

        switch(playerNumber) {
            case 1: {
                Player player = new Player(username, 1, true, "/hunter.png", 0, 10, true, 0);
                playerList.add(player);
                return playerList;
            }
            case 2: {
                Player player = new Player(username, 2, false, "/witch.png", 19, 5, true, 0);
                playerList.add(player);
                return playerList;
            }
            case 3: {
                Player player = new Player(username, 3, false, "/zombie.png", 19, 10, true, 0);
                playerList.add(player);
                return playerList;
            }
            case 4: {
                Player player = new Player(username, 4, false, "/viking.png", 19, 15, true, 0);
                playerList.add(player);
                return playerList;
            }
            default:
                return playerList;
        }

    }

    @MessageMapping("/new-round")
    @SendTo("/destroy/players")
    public List<Player> updatePlayersForNewRound(Player oldPlayer) {

        for(int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getUsername().equals(oldPlayer.getUsername())) {
                playerList.remove(i);
                break;
            }
        }

        Player player = null;
        switch(oldPlayer.getPlayerNumber()) {
            case 1: {
                player = new Player(oldPlayer.getUsername(), 2, false, oldPlayer.getColour(), 19, 5, true, oldPlayer.getScore());
                break;
            }
            case 2: {
                player = new Player(oldPlayer.getUsername(), 3, false,  oldPlayer.getColour(), 19, 10, true, oldPlayer.getScore());
                break;
            }
            case 3: {
                player = new Player(oldPlayer.getUsername(), 4, false,  oldPlayer.getColour(), 19, 15, true, oldPlayer.getScore());
                break;
            }
            case 4: {               
                player = new Player(oldPlayer.getUsername(), 1, true,  oldPlayer.getColour(), 0, 10, true, oldPlayer.getScore());
                break;
            }
            default:
                break;
        }

        playerList.add(player);

        /****** ROUND START NOW WAITS FOR ALL PLAYERS TO BE PROCESSED BY SERVER ******/
        playersReadyMap.put(oldPlayer.getUsername(), true);
        System.out.println(playersReadyMap);
        if (playersReadyMap.size() == playerList.size()) {
            messagingTemplate.convertAndSend("/destroy/round-start-confirmation", "Round start");
            playersReadyMap.clear();
        }

        return playerList;
    }
    
    @MessageMapping("/update-player-movement")
    @SendTo("/destroy/players")
    public List<Player> updatePlayerMovement(Player updatedPlayer) {
        for(Player player : playerList) {
            if (player.getUsername().equals(updatedPlayer.getUsername().toString())) {
                player.setY(updatedPlayer.getY());
                return playerList;
            }
        }
        return playerList;
        
    }

    @MessageMapping("/update-player-active")
    @SendTo("/destroy/players")
    public List<Player> updatePlayerActive(Player updatedPlayer) {
        for(Player player : playerList) {
            if (player.getUsername().equals(updatedPlayer.getUsername().toString())) {
                player.setActive(updatedPlayer.isActive());
                return playerList;
            }
        }
        return playerList;
        
    }

    @MessageMapping("/update-player-score")
    @SendTo("/destroy/player-scores")
    public List<Player> updatePlayerScore(Player updatedPlayer) {
        for(Player player : playerList) {
            if (player.getUsername().equals(updatedPlayer.getUsername().toString())) {
                player.setScore(updatedPlayer.getScore());
            }
        }
        return playerList;
        
    }

    @MessageMapping("/new-bullet")
    @SendTo("/destroy/bullets") 
    public Bullet newBullet(Bullet bullet) {
        return bullet;
    }

    @MessageMapping("/game-end")
    @SendTo("/destroy/players")
    public List<Player> emptyPlayerList() {
        playerList.clear();
        return playerList;
        
    }

    @MessageMapping("/new-game")
    @SendTo("/destroy/new-game")
    public List<Player> newGame() {
        for(Player player: playerList) {
            player.setScore(0);
            player.setActive(true);
        }
        return playerList;
    }

    @MessageMapping("/final-score")
    @SendTo("/destroy/final-score-confirmation")
    public List<Player> finalScores(String username) {
        playerFinalScores.add(username);
        if (playerFinalScores.size() == 3) {
            playerFinalScores.clear();
            return playerList;
        } else {
            return Collections.emptyList();
        }        
    }

}
