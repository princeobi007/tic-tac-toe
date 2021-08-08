package com.poiwuorie.tictactoe.resource;


import com.poiwuorie.tictactoe.model.BoardObject;
import com.poiwuorie.tictactoe.service.OptimalPlayService;
import com.poiwuorie.tictactoe.util.TicTacToeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/tic-tac-toe")
public class GameRestController {

    @Autowired
    OptimalPlayService optimalPlayService;

    @GetMapping("/play")
    public ResponseEntity<?> getPathInfo(@RequestParam("board") String userPlay, HttpSession session, HttpServletRequest req) {

        log.info("Controller Borad : {}", userPlay);
        char [] plays = userPlay.toCharArray();

        BoardObject boardObject = new BoardObject(plays);

        boolean valid = TicTacToeUtil.isValidPlay(plays);

        if(!valid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boardObject = optimalPlayService.checkWin(boardObject);

        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.blockWin(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.createFork(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.blockFork(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.centerPlay(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.oppositeCorner(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.emptyCorner(boardObject);
        }
        if(!boardObject.getHasPlayedTurn()) {
            boardObject = optimalPlayService.emptySide(boardObject);
        }

        StringBuilder builder = new StringBuilder();

        for(char c: boardObject.getPlays()) {
            builder.append(c);
        }
        return   ResponseEntity.ok(builder.toString());

    }
}
