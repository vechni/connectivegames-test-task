package com.semyonov.module.worker.game;

import com.semyonov.model.Response;
import com.semyonov.model.SideCoin;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.GameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class PlayerEmulator
{
    private int countCoins;
    private Random random;
    private String token = UUID.randomUUID().toString();
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    PlayerEmulator( final int countCoins ){
        this.countCoins = countCoins;
        random = new Random();
    }

    public String getToken(){
        return token;
    }

    public int placeBet(){
        // The player lost everything
        if( countCoins < 0 ){
            return 0;
        }

        if( countCoins == GameUtils.MIN_BET ){
            return GameUtils.MIN_BET;
        }

        final int bound = countCoins - GameUtils.MIN_BET;
        // The player has no coins to make the minimum bet
        if( bound < 0 ){
            return 0;
        }

        final int bet = random.nextInt(bound) + GameUtils.MIN_BET;
        log.debug("Token: " + token + ", bet: " + bet);
        return bet;
    }

    public SideCoin chooseSideCoin(){
        return SideCoin.getRandomSideCoin();
    }

    public void updateBalance( final Response response ){
        if( response.isWin() ){
            countCoins = countCoins + response.getBet() + response.getPrize();
        }else{
            countCoins -= response.getBet();
        }
    }
}
