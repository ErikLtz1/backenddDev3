package com.backenddev3.backenddev3.controllers;

import com.backenddev3.backenddev3.models.Bullet;
import com.backenddev3.backenddev3.models.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class StompControllerTest {

    private StompController stompController;

    @BeforeEach
    public void setUp() {
        mock(SimpMessagingTemplate.class);
        stompController = new StompController();
    }

    @Test
    public void testAddFirstPlayer() {
        List<Player> playerResult = stompController.newPlayer("player1");
        assertEquals(1, playerResult.size());
        assertEquals("player1", playerResult.get(0).getUsername());
        assertEquals(1, playerResult.get(0).getPlayerNumber());
        assertEquals("public/hunter.png", playerResult.get(0).getColour());
        assertEquals(0, playerResult.get(0).getX());
        assertEquals(10, playerResult.get(0).getY());
        assertEquals(true, playerResult.get(0).isShooter());
        assertEquals(true, playerResult.get(0).isActive());
        assertEquals(0, playerResult.get(0).getScore());
    }

    @Test
    public void testAddSecondPlayer() {
        stompController.newPlayer("player1");
        List<Player> playerResult = stompController.newPlayer("player2");
        assertEquals(2, playerResult.size());
        assertEquals("player2", playerResult.get(1).getUsername());
        assertEquals(2, playerResult.get(1).getPlayerNumber());
        assertEquals("public/witch.png", playerResult.get(1).getColour());
        assertEquals(19, playerResult.get(1).getX());
        assertEquals(5, playerResult.get(1).getY());
        assertEquals(false, playerResult.get(1).isShooter());
        assertEquals(true, playerResult.get(1).isActive());
        assertEquals(0, playerResult.get(1).getScore());
    }

    @Test
    public void testAddThirdPlayer() {
        stompController.newPlayer("player1");
        stompController.newPlayer("player2");
        List<Player> playerResult = stompController.newPlayer("player3");
        assertEquals(3, playerResult.size());
        assertEquals("player3", playerResult.get(2).getUsername());
        assertEquals(3, playerResult.get(2).getPlayerNumber());
        assertEquals("public/zombie.png", playerResult.get(2).getColour());
        assertEquals(19, playerResult.get(2).getX());
        assertEquals(10, playerResult.get(2).getY());
        assertEquals(false, playerResult.get(2).isShooter());
        assertEquals(true, playerResult.get(2).isActive());
        assertEquals(0, playerResult.get(2).getScore());
    }

    @Test
    public void testAddFourthPlayer() {
        stompController.newPlayer("player1");
        stompController.newPlayer("player2");
        stompController.newPlayer("player3");
        List<Player> playerResult = stompController.newPlayer("player4");
        assertEquals(4, playerResult.size());
        assertEquals("player4", playerResult.get(3).getUsername());
        assertEquals(4, playerResult.get(3).getPlayerNumber());
        assertEquals("public/viking.png", playerResult.get(3).getColour());
        assertEquals(19, playerResult.get(3).getX());
        assertEquals(15, playerResult.get(3).getY());
        assertEquals(false, playerResult.get(3).isShooter());
        assertEquals(true, playerResult.get(3).isActive());
        assertEquals(0, playerResult.get(3).getScore());
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


}