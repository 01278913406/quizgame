package org.chicha.quizgame.domain.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import org.chicha.quizgame.data.repository.StatsRepo;
import org.chicha.quizgame.domain.entity.StatEntity;

public class StatsViewModel extends ViewModel {
    private final LiveData<List<StatEntity>> allStats;
    private final StatsRepo statsRepo;

    @Inject
    public StatsViewModel(StatsRepo statsRepo) {
        this.statsRepo = statsRepo;
        this.allStats = statsRepo.getAllStats();
    }

    public LiveData<List<StatEntity>> getAllStats() {
        return allStats;
    }

    public void insertStats(StatEntity statEntity) {
        statsRepo.insertStats(statEntity);
    }

    public void insertStats(StatEntity... statEntities) {
        statsRepo.insertStats(statEntities);
    }

    public void deleteStats() {
        statsRepo.deleteStats();
    }

    public void deleteStatsById(int id) {
        statsRepo.deleteStatsById(id);
    }

    public LiveData<List<StatEntity>> getStatsByTag(String tag) {
        return statsRepo.getStatsByTag(tag);
    }

}
