package com.semyonov.module.statistic;

import com.semyonov.model.PlayerStatistic;

public interface Statistic
{
    void saveStatisticPlayer( PlayerStatistic playerStatistic );

    void showStatisticPlayer( PlayerStatistic playerStatistic );

    void showStatisticPlayers();
}
