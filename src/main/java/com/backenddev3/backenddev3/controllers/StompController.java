package com.backenddev3.backenddev3.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.*;
import com.backenddev3.backenddev3.models.Player;

@Controller
public class StompController {

    private List<Player> playerList = new ArrayList<>();
    
    @MessageMapping("/new-player")
    @SendTo("/destroy/players")
    public List<Player> newPlayer(String username) {

        System.out.println(username + " received");
        System.out.println(playerList.size());
        if (playerList.size() == 4) {
            return playerList;
        }

        int playerNumber = playerList.size() + 1;

        switch(playerNumber) {
            case 1: {
                Player player = new Player(username, 1, true, "blue", 0, 10, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 2: {
                Player player = new Player(username, 2, false, "red", 19, 5, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 3: {
                Player player = new Player(username, 3, false, "green", 19, 10, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 4: {
                Player player = new Player(username, 4, false, "yellow", 19, 15, true, 0);
                playerList.add(player);
                return playerList;
            }
            default:
                return playerList;
        }

    }
    
}
