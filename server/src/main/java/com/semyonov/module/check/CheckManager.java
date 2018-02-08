package com.semyonov.module.check;

import com.semyonov.data.socket.Bridge;
import com.semyonov.model.Player;
import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.StatusRequest;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.GameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckManager
{
    private static final String EMPTY_STRING = "";
    private static final String NO_HAS_MORE_COINS = "no has more coins";
    private static final String NOT_ENOUGH_COIN = "not enough coins";
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    public boolean isNotCorrectParam( final Player player, final Request request, final Bridge bridge ){

        if( request == null ){
            log.debug("Request is null");
            bridge.sendResponse(new Response(StatusRequest.ERROR, EMPTY_STRING, 0, GameUtils.DEFAULT_PRIZE, false));
            return true;
        }

        if( request.getToken().isEmpty() || (request.getSideCoin() == null) || (request.getBet() == 0) ){
            log.debug("Request fields is null, " + player.toString());
            bridge.sendResponse(new Response(StatusRequest.ERROR, EMPTY_STRING, request.getBet(), GameUtils.DEFAULT_PRIZE, false));
            return true;
        }

        if( player.getCoins() < GameUtils.MIN_BET ){
            log.debug("No has more coins, " + player.toString());
            bridge.sendResponse(new Response(StatusRequest.SUCCESS, NO_HAS_MORE_COINS, request.getBet(), GameUtils.DEFAULT_PRIZE, false));
            return true;
        }

        if( player.getCoins() < request.getBet() ){
            log.debug("Not enough coins, " + player.toString());
            bridge.sendResponse(new Response(StatusRequest.SUCCESS, NOT_ENOUGH_COIN, request.getBet(), GameUtils.DEFAULT_PRIZE, false));
            return true;
        }

        return false;
    }
}
