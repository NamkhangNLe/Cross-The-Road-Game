package com.example.myapplication;

public class GameInstance {
    private Player player;
    private String difficulty;

    public GameInstance(Player player, String difficulty){
        this.player = player;
        this.difficulty = difficulty;

    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player newPlayer){
        this.player = newPlayer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String newDifficulty)  {
        this.difficulty = newDifficulty;
    }
}

