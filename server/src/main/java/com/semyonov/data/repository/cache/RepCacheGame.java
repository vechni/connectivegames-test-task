package com.semyonov.data.repository.cache;

import com.semyonov.data.repository.Repository;
import com.semyonov.model.GameResult;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RepCacheGame
        implements Repository<GameResult>
{
    private static RepCacheGame instance = null;
    private ConcurrentMap<String, GameResult> map = new ConcurrentHashMap<>();

    private RepCacheGame(){ }

    public static synchronized RepCacheGame getInstance(){
        if( instance == null ){
            instance = new RepCacheGame();
        }

        return instance;
    }

    @Override
    public void add( final GameResult item ){
        map.put(item.getId(), item);
    }

    @Override
    public void add( final List<GameResult> items ){
        for( GameResult item : items ){
            map.put(item.getId(), item);
        }
    }

    @Override
    public void update( final GameResult item ){
        map.replace(item.getId(), item);
    }

    @Override
    public void update( final List<GameResult> items ){
        for( GameResult item : items ){
            map.replace(item.getId(), item);
        }
    }

    @Override
    public void remove( final GameResult item ){
        map.remove(item.getId());
    }

    @Override
    public void remove( final List<GameResult> items ){
        for( GameResult item : items ){
            map.remove(item.getId());
        }
    }

    @Override
    public void removeAll(){
        map.clear();
    }

    @Override
    public GameResult fetchById( final String id ){
        return map.get(id);
    }
}
