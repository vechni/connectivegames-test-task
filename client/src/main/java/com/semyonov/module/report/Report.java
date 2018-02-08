package com.semyonov.module.report;

import com.semyonov.model.PlayerStatistic;

import java.util.List;

public interface Report
{
    void printStatisticPlayers( List<PlayerStatistic> playerStatistic );

    void printStatisticPlayer( PlayerStatistic statistic );
}
