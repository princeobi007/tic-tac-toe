package com.poiwuorie.tictactoe.model;

public class BoardObject {
    private char[] plays;
    private boolean hasPlayedTurn = false;

    public BoardObject(char[] plays){
        this.plays = plays;
    }

    public char[] getPlays() {
        return plays;
    }

    public void setPlays(char[] plays) {
        this.plays = plays;
    }

    public void setHasPlayedTurn(boolean hasPlayedTurn) {
        this.hasPlayedTurn = hasPlayedTurn;
    }

    public boolean getHasPlayedTurn() {
        return hasPlayedTurn;
    }
}
