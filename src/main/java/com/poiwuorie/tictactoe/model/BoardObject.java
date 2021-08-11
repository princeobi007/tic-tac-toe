package com.poiwuorie.tictactoe.model;

import lombok.Data;

@Data
public class BoardObject {
    private char[] plays;
    private boolean turnPlayed = false;
    public BoardObject(char[] plays){
        this.plays = plays;
    }

}
