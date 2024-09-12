package com.backenddev3.backenddev3.controllers;

import com.backenddev3.backenddev3.models.Bullet;
import com.backenddev3.backenddev3.models.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class StompControllerTest {

    private StompController stompController;
    private List<Player> playerList;

    @BeforeEach
    public void setUp() {
        mock(SimpMessagingTemplate.class);
        stompController = new StompController();
        playerList = new ArrayList<>();
        stompController.setPlayerList(playerList);
    }

    @Test
    public void testAddFirstPlayer() {
        List<Player> playerResult = stompController.newPlayer("player1");
        assertPlayer(playerResult.get(0), "player1", 1, "/hunter.png", 0, 10, true, true, 0);
        assertEquals(1, playerResult.size());
    }

    @Test
    public void testAddSecondPlayer() {
        stompController.newPlayer("player1");
        List<Player> playerResult = stompController.newPlayer("player2");
        assertPlayer(playerResult.get(1), "player2", 2, "/witch.png", 19, 5, false, true, 0);
        assertEquals(2, playerResult.size());
    }

    @Test
    public void testAddThirdPlayer() {
        stompController.newPlayer("player1");
        stompController.newPlayer("player2");
        List<Player> playerResult = stompController.newPlayer("player3");
        assertPlayer(playerResult.get(2), "player3", 3, "/zombie.png", 19, 10, false, true, 0);
        assertEquals(3, playerResult.size());
    }

    @Test
    public void testAddFourthPlayer() {
        stompController.newPlayer("player1");
        stompController.newPlayer("player2");
        stompController.newPlayer("player3");
        List<Player> playerResult = stompController.newPlayer("player4");
        assertPlayer(playerResult.get(3), "player4", 4, "/viking.png", 19, 15, false, true, 0);
        assertEquals(4, playerResult.size());
    }

    @Test
    public void testAddFifthPlayer() {
        stompController.newPlayer("player1");
        stompController.newPlayer("player2");
        stompController.newPlayer("player3");
        stompController.newPlayer("player4");
        List<Player> playerResult = stompController.newPlayer("player5");
        assertEquals(4, playerResult.size());
        assertFalse(playerResult.stream().anyMatch(player -> player.getUsername().equals("player5")));
    }


    @Test
    public void testNewBullet() {
        Bullet bullet = new Bullet();
        Bullet bulletResult = stompController.newBullet(bullet);
        assertEquals(bullet, bulletResult);
    }

    @Test
    public void testUpdatePlayerMovement_PlayerExists() {
        List<Player> existingPlayers = stompController.newPlayer("player1");
        existingPlayers.get(0);
        Player updatedPlayer = new Player("player1", 1, true, "/hunter.png", 0, 20, true, 0);
        List<Player> result = stompController.updatePlayerMovement(updatedPlayer);
        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getY());
    }

    private void assertPlayer(Player player, String username, int playerNumber, String colour, int x, int y, boolean shooter, boolean active, int score) {
        assertEquals(username, player.getUsername());
        assertEquals(playerNumber, player.getPlayerNumber());
        assertEquals(colour, player.getColour());
        assertEquals(x, player.getX());
        assertEquals(y, player.getY());
        assertEquals(shooter, player.isShooter());
        assertEquals(active, player.isActive());
        assertEquals(score, player.getScore());
    }

}