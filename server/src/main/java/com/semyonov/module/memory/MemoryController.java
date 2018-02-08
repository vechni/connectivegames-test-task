package com.semyonov.module.memory;

import com.semyonov.data.repository.Repository;
import com.semyonov.data.repository.cache.RepCacheGame;
import com.semyonov.data.repository.cache.RepCachePlayer;
import com.semyonov.model.GameResult;
import com.semyonov.model.Player;
import com.semyonov.utils.GameUtils;

public class MemoryController
{
    private Player player;
    private int tempCoins;
    private Repository<Player> repPlayer = RepCachePlayer.getInstance();
    private Repository<GameResult> repGame = RepCacheGame.getInstance();

    public void saveGame( final GameResult gameResult ){
        player = repPlayer.fetchById(gameResult.getPlayerToken());
        tempCoins = player.getCoins();

        if( gameResult.isWin() ){
            tempCoins += gameResult.getPrize();
        }else{
            tempCoins -= gameResult.getBet();
        }

        repPlayer.update(new Player(player.getToken(), tempCoins));
        repGame.add(gameResult);
    }

    public Player getPlayer( final String token ){
        player = repPlayer.fetchById(token);
        if( player == null ){
            player = new Player(token, GameUtils.START_COUNT_COINS);
            repPlayer.add(player);
        }
        return player;
    }
}
