package com.semyonov.module.game;

import com.semyonov.model.GameResult;
import com.semyonov.model.Request;
import com.semyonov.model.SideCoin;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.GameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class GameFiftyPercentWinController
        implements GameController
{
    private String id;
    private SideCoin sideCoin;
    private double prizeFactor = 0;
    private int prize = GameUtils.DEFAULT_PRIZE;
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    public GameFiftyPercentWinController( final double prizeFactor ){
        this.prizeFactor = prizeFactor;
    }

    public GameResult calculateWin( final Request request ){
        id = UUID.randomUUID().toString();
        sideCoin = SideCoin.getRandomSideCoin();

        if( sideCoin == request.getSideCoin() ){
            prize = calculatePrize(request);
            log.debug("Player - " + request.toString() + " --- is win, prize: " + prize + ", random side coin is: " + sideCoin);
            return new GameResult(id,
                                  request.getToken(),
                                  request.getTime(),
                                  request.getBet(),
                                  true,
                                  prize);
        }else{
            log.debug("Player - " + request.toString() + " --- is lose, random side coin is: " + sideCoin);
            return new GameResult(id,
                                  request.getToken(),
                                  request.getTime(),
                                  request.getBet(),
                                  false,
                                  GameUtils.DEFAULT_PRIZE);
        }
    }

    private int calculatePrize( final Request request ){
        return (int) ((request.getBet() * prizeFactor) - request.getBet());
    }
}
