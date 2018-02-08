package com.semyonov.data.repository.cache;

import com.semyonov.data.repository.Repository;
import com.semyonov.model.Player;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RepCachePlayer
        implements Repository<Player>
{
    private static RepCachePlayer instance = null;
    private ConcurrentMap<String, Player> map = new ConcurrentHashMap<>();

    private RepCachePlayer(){ }

    public static synchronized RepCachePlayer getInstance(){
        if( instance == null ){
            instance = new RepCachePlayer();
        }

        return instance;
    }

    @Override
    public void add( final Player item ){
        map.put(item.getToken(), item);
    }

    @Override
    public void add( final List<Player> items ){
        for( Player item : items ){
            map.put(item.getToken(), item);
        }
    }

    @Override
    public void update( final Player item ){
        map.replace(item.getToken(), item);
    }

    @Override
    public void update( final List<Player> items ){
        for( Player item : items ){
            map.replace(item.getToken(), item);
        }
    }

    @Override
    public void remove( final Player item ){
        map.remove(item.getToken());
    }

    @Override
    public void remove( final List<Player> items ){
        for( Player item : items ){
            map.remove(item.getToken());
        }
    }

    @Override
    public void removeAll(){
        map.clear();
    }

    @Override
    public Player fetchById( final String id ){
        return map.get(id);
    }
}
