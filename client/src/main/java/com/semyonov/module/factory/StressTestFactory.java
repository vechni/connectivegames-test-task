package com.semyonov.module.factory;

import com.semyonov.module.worker.Worker;
import com.semyonov.module.worker.game.GameLaunchWorker;

public class StressTestFactory
        implements Factory
{
    public Worker create(){
        return new GameLaunchWorker();
    }
}