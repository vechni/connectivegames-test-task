package com.semyonov.data.repository;

import java.util.List;

public interface Repository<T>
{
    void add( T item );

    void add( List<T> items );

    void update( T item );

    void update( List<T> items );

    void remove( T item );

    void remove( List<T> items );

    void removeAll();

    T fetchById( String id );
}
