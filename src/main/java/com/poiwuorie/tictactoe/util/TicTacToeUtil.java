package com.poiwuorie.tictactoe.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class TicTacToeUtil {
    private static final HashMap<Integer, List<int[]>> winMaps = new HashMap<>();
    private static final HashMap<Integer, List<int []>> forkMaps = new HashMap<>();

    private static final int[] win1 = { 0, 1, 2 };
    private static final int[] win2 = { 3, 4, 5 };
    private static final int[] win3 = { 6, 7, 8 };
    private static final int[] win4 = { 0, 3, 6 };
    private static final int[] win5 = { 1, 4, 7 };
    private static final int[] win6 = { 0, 4, 8 };
    private static final int[] win7 = { 2, 4, 6 };
    private static final int[] win8 = { 2, 5, 8 };

    static {
        List<int []> zeroIndexWin = new ArrayList<>();
        zeroIndexWin.add(win1);
        zeroIndexWin.add(win4);
        zeroIndexWin.add(win6);
        winMaps.put(0, zeroIndexWin);

        List<int []> oneIndexWin = new ArrayList<>();
        oneIndexWin.add(win1);
        oneIndexWin.add(win5);
        winMaps.put(1, oneIndexWin);

        List<int []> twoIndexWin = new ArrayList<>();
        twoIndexWin.add(win1);
        twoIndexWin.add(win7);
        twoIndexWin.add(win8);
        winMaps.put(2, twoIndexWin);

        List<int []> threeIndexWin = new ArrayList<>();
        threeIndexWin.add(win2);
        threeIndexWin.add(win4);
        winMaps.put(3, threeIndexWin);

        List<int []> fourIndexWin = new ArrayList<>();
        fourIndexWin.add(win2);
        fourIndexWin.add(win5);
        fourIndexWin.add(win6);
        fourIndexWin.add(win7);
        winMaps.put(4, fourIndexWin);


        List<int []> fiveIndexWin = new ArrayList<>();
        fiveIndexWin.add(win2);
        fiveIndexWin.add(win8);
        winMaps.put(5, fiveIndexWin);

        List<int []> sixIndexWin = new ArrayList<>();
        sixIndexWin.add(win3);
        sixIndexWin.add(win4);
        sixIndexWin.add(win7);
        winMaps.put(6, sixIndexWin);

        List<int []> sevenIndexWin = new ArrayList<>();
        sevenIndexWin.add(win3);
        sevenIndexWin.add(win5);
        winMaps.put(7, sevenIndexWin);

        List<int []> eightIndexWin = new ArrayList<>();
        eightIndexWin.add(win6);
        eightIndexWin.add(win3);
        eightIndexWin.add(win8);
        winMaps.put(8, eightIndexWin);


        List<int []> forkPositionZero = new ArrayList<>();
        forkPositionZero.add(win1);
        forkPositionZero.add(win6);
        forkPositionZero.add(win4);
        forkMaps.put(0,forkPositionZero);

        List<int []> forkPositionTwo = new ArrayList<>();
        forkPositionTwo.add(win1);
        forkPositionTwo.add(win7);
        forkPositionTwo.add(win8);
        forkMaps.put(2,forkPositionTwo);

        List<int []> forkPositionSix = new ArrayList<>();
        forkPositionZero.add(win3);
        forkPositionZero.add(win7);
        forkPositionZero.add(win4);
        forkMaps.put(6,forkPositionSix);

        List<int []> forkPositionEight = new ArrayList<>();
        forkPositionEight.add(win3);
        forkPositionEight.add(win8);
        forkPositionEight.add(win6);
        forkMaps.put(8,forkPositionEight);
    }

    public static boolean isValidPlay(char[] board) {

        if (board.length < 9) {
            log.info("Invalid length for board");
            return false;
        }
        for (char c : board) {
            if (c == 'x' || c == 'o' || c == ' ') {
                log.info("c is: " + c);

            } else {
                return false;
            }
        }
        return true;
    }

    public static  List<int[]> getWinCombinations(int position) {

        if(position < 0 || position > 8) {
            throw new RuntimeException("Position out of bounds");
        }

        return winMaps.get(position);
    }

    public static  List<int[]> getForkPlays(int position) {

        if(position < 0 || position > 8) {
            throw new RuntimeException("Position out of bounds");
        }

        if(position == 0 || position == 2|| position ==6 || position ==8){
            return forkMaps.get(position);
        }

        return null;
    }

}
