package com.backenddev3.backenddev3.models;

public class Player {
    
    private String username;
    private int playerNumber;
    private boolean shooter;
    private String colour;
    private int x;
    private int y;
    private boolean active;
    private int score;

    public Player() {
    }
    public Player(String username, int playerNumber, boolean shooter, String colour, int x, int y, boolean active, int score) {
        this.username = username;
        this.playerNumber = playerNumber;
        this.shooter = shooter;
        this.colour = colour;
        this.active = active;
        this.score = score;
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public boolean isShooter() {
        return shooter;
    }
    public void setShooter(boolean shooter) {
        this.shooter = shooter;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    
}
