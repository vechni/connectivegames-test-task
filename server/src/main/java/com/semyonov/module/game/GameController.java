package com.semyonov.module.game;

import com.semyonov.model.GameResult;
import com.semyonov.model.Request;

public interface GameController
{
    GameResult calculateWin( Request request );
}
