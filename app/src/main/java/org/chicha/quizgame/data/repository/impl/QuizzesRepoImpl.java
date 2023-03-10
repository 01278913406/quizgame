package org.chicha.quizgame.data.repository.impl;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import org.chicha.quizgame.data.data_source.local.dao.QuizzesDao;
import org.chicha.quizgame.data.repository.QuizzesRepo;
import org.chicha.quizgame.database.QuizzesDatabase;
import org.chicha.quizgame.domain.entity.QuizEntity;

public class QuizzesRepoImpl implements QuizzesRepo {

   public QuizzesDao dao;
   private final LiveData<List<QuizEntity>> allQuizzes;

    @Inject
    public QuizzesRepoImpl(QuizzesDao dao) {
         this.dao = dao;
         this.allQuizzes = this.dao.getAllQuizzes();
    }

    @Override
    public LiveData<List<QuizEntity>> getAllQuizzes() {
        return allQuizzes;
    }

    @Override
    public LiveData<List<QuizEntity>> getQuizzesByCategory(String category) {
        return dao.getQuizzesByCategory(category);
    }

    @Override
    public void insertQuiz(QuizEntity quiz) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> dao.insertQuiz(quiz));
    }

    @Override
    public void insertQuizzes(QuizEntity... quizzes) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> dao.insertQuizzes(quizzes));
    }

    @Override
    public void deleteQuiz(long id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> dao.deleteQuiz(id));
    }

    @Override
    public void deleteAllQuizzes() {
        QuizzesDatabase.databaseWriteExecutor.execute(dao::deleteAllQuizzes);
    }

    @Override
    public QuizEntity getQuizById(long id) {
        return dao.getQuizById(id);
    }

    @Override
    public int getSizeQuizzesTags(String tags) {
        return dao.getSizeQuizzesTags(tags);
    }

    @Override
    public int start() {
        return dao.start();
    }
}
