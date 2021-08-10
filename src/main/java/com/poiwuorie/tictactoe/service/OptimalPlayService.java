package com.poiwuorie.tictactoe.service;

import com.poiwuorie.tictactoe.model.BoardObject;

public interface OptimalPlayService {

    public BoardObject checkWin(BoardObject playObject);

    public BoardObject blockWin(BoardObject playObject);

    public BoardObject createFork(BoardObject playObject);

    public BoardObject blockFork(BoardObject playObject);

    public BoardObject centerPlay(BoardObject playObject);

    public BoardObject oppositeCorner(BoardObject playObject);

    public BoardObject emptyCorner(BoardObject playObject);

    public BoardObject emptySide(BoardObject playObject);

}
