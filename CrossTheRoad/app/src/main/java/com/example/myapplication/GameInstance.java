package com.example.myapplication;

public class GameInstance {
    private Player player;
    private String difficulty;
    private int difficultyNum;

    public GameInstance(Player player, String difficulty) {
        this.player = player;
        this.difficulty = difficulty;
        switch (difficulty) {
        case "Easy":
            difficultyNum = 1;
            break;
        case "Medium":
            difficultyNum = 2;
            break;
        case "Hard":
            difficultyNum = 3;
            break;
        default:
            break;
        }
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String newDifficulty)  {
        this.difficulty = newDifficulty;
    }


    public int getDifficultyNum() {
        return difficultyNum;
    }

    public void setDifficultyNum(int newDifficultyNum) {
        difficultyNum = newDifficultyNum;
    }
}

