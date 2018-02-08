package com.semyonov.module.report;

import com.semyonov.model.PlayerStatistic;
import com.semyonov.utils.ClassNameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReportImpl
        implements Report
{
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    @Override
    public void printStatisticPlayers( final List<PlayerStatistic> statistic ){
        for( PlayerStatistic playerStatistic : statistic ){
            log.debug("Print statistic of player:" + playerStatistic.toString());
            System.out.format("Token: %32s,  Success: %1d,  Failed: %1d,  Average time: %5f ms, %2s \n",
                              playerStatistic.getToken(),
                              playerStatistic.getCountSuccessRequest(),
                              playerStatistic.getCountFailedRequest(),
                              playerStatistic.getAverageTime(),
                              playerStatistic.getWarning());
        }
    }

    @Override
    public void printStatisticPlayer( final PlayerStatistic playerStatistic ){
        log.debug("Print statistic of player:" + playerStatistic.toString());
        System.out.format("Token: %32s,  Success: %1d,  Failed: %1d,  Average time: %5f ms,  %2s \n",
                          playerStatistic.getToken(),
                          playerStatistic.getCountSuccessRequest(),
                          playerStatistic.getCountFailedRequest(),
                          playerStatistic.getAverageTime(),
                          playerStatistic.getWarning());
    }
}
