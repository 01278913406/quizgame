package org.chicha.quizgame.data.repository.impl;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.chicha.quizgame.data.data_source.local.dao.AnswersDao;
import org.chicha.quizgame.data.repository.AnswersRepo;
import org.chicha.quizgame.database.QuizzesDatabase;
import org.chicha.quizgame.domain.entity.Answers;

public class AnswersRepoImpl implements AnswersRepo {
   private final AnswersDao answersDao;
   private final LiveData<List<Answers>> allAnswers;

    @Inject
    public AnswersRepoImpl(AnswersDao dao) {
            this.answersDao = dao;
            this.allAnswers = answersDao.getAnswers();
    }

    @Override
    public LiveData<List<Answers>> getAllAnswers() {
        return allAnswers;
    }

    @Override
    public void insertAnswer(Answers answer) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswer(answer));
    }

    @Override
    public Answers getAnswerById(long id) {
        try {
            return QuizzesDatabase.databaseWriteExecutor.submit(() -> answersDao.getAnswerById(id)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAnswer(Answers answer) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswer(answer));
    }

    @Override
    public void insertAnswers(Answers... answers) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswers(answers));
    }

    @Override
    public void deleteAnswer(long id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.deleteAnswer(id));
    }

    @Override
    public void deleteAllAnswers() {
        QuizzesDatabase.databaseWriteExecutor.execute(answersDao::deleteAllAnswers);
    }

    @Override
    public int start() {
        return answersDao.start();
    }
}
