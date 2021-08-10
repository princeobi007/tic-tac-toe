package com.poiwuorie.tictactoe.serviceImpl;


import com.poiwuorie.tictactoe.model.BoardObject;
import com.poiwuorie.tictactoe.service.OptimalPlayService;
import com.poiwuorie.tictactoe.util.TicTacToeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class OptimalPlayServiceImpl implements OptimalPlayService {

    @Override
    public BoardObject checkWin(BoardObject playObject) {
        log.info("Checking Win Scenario");
        int currentPosition = 0;
        for (char c : playObject.getPlays()) {
            log.info("Checking for position: " + c);
            if (c != 'o') {
                log.warn("current character not our play or empty: " + c);
                continue;
            }

            List<int[]> winPlays = TicTacToeUtil.getWinCombinations(currentPosition);

            for (int[] currentWinPlay : winPlays) {

                if (opponentHasPlayedPosition(playObject, currentWinPlay, 'x')) {
                    log.warn("opponent played win position ");
                    continue;
                }

                if (emptySlotsGreaterThanOne(playObject, currentWinPlay)) {
                    log.warn("empty slot > 1");
                    continue;
                }

                fillEmptyPosition(playObject, currentWinPlay);
                log.info("Filling win position ");

            }
            currentPosition++;
        }
        return playObject;
    }

    @Override
    public BoardObject blockWin(BoardObject playObject) {
        // TODO Auto-generated method stub
        int currentPosition = 0;
        for (char c : playObject.getPlays()) {

            if (c != 'x') {
                continue;
            }

            List<int[]> winPlays = TicTacToeUtil.getWinCombinations(currentPosition);

            for (int[] currentWinPlay : winPlays) {

                if (opponentHasPlayedPosition(playObject, currentWinPlay, 'o')) {
                    log.warn("opponent played win position ");
                    continue;
                }

                if (emptySlotsGreaterThanOne(playObject, currentWinPlay)) {
                    log.warn("empty slot > 1");
                    continue;
                }

                fillEmptyPosition(playObject, currentWinPlay);
                log.info("Filling win position ");

            }
            currentPosition++;
        }
        return playObject;
    }

    @Override
    public BoardObject createFork(BoardObject playObject) {
        int currentPosition = 0;
        for (char c : playObject.getPlays()) {
            currentPosition++;
            List<int []> forkPlays = null;
            if (c != 'x') {
                forkPlays = TicTacToeUtil.getForkPlays(currentPosition);
            }

            if(forkPlays != null){
                //check the ones that have been filled and qualifies for the fork
                for (int[] currentForkPlay : forkPlays) {

                    if (opponentHasPlayedPosition(playObject, currentForkPlay, 'x')) {
                        log.warn("opponent played fork position ");
                        continue;
                    }

                    if (emptySlotsGreaterThanOne(playObject, currentForkPlay)) {
                        fillEmptyPosition(playObject, currentForkPlay);
                        log.info("Filling win position ");
                        break;
                    }
                }

            }
        }
        return playObject;
    }

    @Override
    public BoardObject blockFork(BoardObject playObject) {
        int currentPosition = 0;
        for (char c : playObject.getPlays()) {
            currentPosition++;
            List<int []> forkPlays = null;
            if (c != 'o') {
                forkPlays = TicTacToeUtil.getForkPlays(currentPosition);
            }

            if(forkPlays != null){
                //check the ones that have been filled and qualifies for the fork
                for (int[] currentForkPlay : forkPlays) {

                    if (opponentHasPlayedPosition(playObject, currentForkPlay, 'o')) {
                        log.warn("opponent played fork position ");
                        continue;
                    }

                    if (emptySlotsGreaterThanOne(playObject, currentForkPlay)) {
                        fillEmptyPosition(playObject, currentForkPlay);
                        log.info("Filling win position ");
                        break;
                    }
                }

            }
        }
        return playObject;
    }

    @Override
    public BoardObject centerPlay(BoardObject playObject) {
        // TODO Auto-generated method stub
        if (playObject.getPlays()[4] == ' ') {
            playObject.getPlays()[4] = 'o';
            playObject.setHasPlayedTurn(true);
        }
        return playObject;
    }

    @Override
    public BoardObject oppositeCorner(BoardObject playObject) {
        if (playObject.getPlays()[0] == 'x' && playObject.getPlays()[8] == ' ') {
            playObject.getPlays()[8] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[8] == ' ' && playObject.getPlays()[0] == ' ') {
            playObject.getPlays()[0] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[6] == ' ' && playObject.getPlays()[2] == ' ') {
            playObject.getPlays()[2] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[2] == ' ' && playObject.getPlays()[6] == ' ') {
            playObject.getPlays()[6] = 'o';
            playObject.setHasPlayedTurn(true);
        }
        return playObject;
    }

    @Override
    public BoardObject emptyCorner(BoardObject playObject) {
        // TODO Auto-generated method stub
        if (playObject.getPlays()[0] == ' ') {
            playObject.getPlays()[0] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[2] == ' ') {
            playObject.getPlays()[2] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[6] == ' ') {
            playObject.getPlays()[6] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[8] == ' ') {
            playObject.getPlays()[8] = 'o';
            playObject.setHasPlayedTurn(true);
        }
        return playObject;
    }

    @Override
    public BoardObject emptySide(BoardObject playObject) {
        // TODO Auto-generated method stub
        if (playObject.getPlays()[1] == ' ') {
            playObject.getPlays()[1] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[5] == ' ') {
            playObject.getPlays()[5] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[7] == ' ') {
            playObject.getPlays()[7] = 'o';
            playObject.setHasPlayedTurn(true);
        } else if (playObject.getPlays()[3] == ' ') {
            playObject.getPlays()[3] = 'o';
            playObject.setHasPlayedTurn(true);
        }
        return playObject;
    }

    private boolean opponentHasPlayedPosition(BoardObject playObject, int[] winPlay, char opponentPlay) {
        boolean canPlay = true;
        for (int position : winPlay) {
            if (playObject.getPlays()[position] == opponentPlay) {
                canPlay = false;
                break;
            }
        }

        return canPlay;
    }

    private boolean emptySlotsGreaterThanOne(BoardObject playObject, int[] winPlay) {
        boolean emptySlotsGreaterThanOne = false;
        int emptySlots = 0;
        for (int position : winPlay) {
            if (playObject.getPlays()[position] == ' ') {
                emptySlots++;
            }
        }

        if (emptySlots > 1) {
            emptySlotsGreaterThanOne = true;
        }

        return emptySlotsGreaterThanOne;
    }

    private BoardObject fillEmptyPosition(BoardObject playObject, int[] winPlay) {
        for (int position : winPlay) {
            if (playObject.getPlays()[position] == ' ') {
                playObject.getPlays()[position] = 'o';
                playObject.setHasPlayedTurn(true);
                break;
            }
        }
        return playObject;
    }
}
