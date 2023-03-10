package org.chicha.quizgame.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import org.chicha.quizgame.domain.entity.Answers;

public interface AnswersRepo {
    LiveData<List<Answers>> getAllAnswers();
    Answers getAnswerById(long id);
    void insertAnswer(Answers answer);
    void insertAnswers(Answers... answers);
    void deleteAllAnswers();
    void deleteAnswer(long id);
    void updateAnswer(Answers answer);
    int start();
}
