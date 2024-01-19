package com.nashss.se.nineam.dependancy;

import com.nashss.se.nineam.activity.DeleteAnswerActivity;
import com.nashss.se.nineam.activity.GetQuestionActivity;
import com.nashss.se.nineam.activity.SaveUserAnswerActivity;
import com.nashss.se.nineam.activity.ViewHistoryActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in My Social Network.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides an instance of GetQuestionActivity.
     *
     * @return The GetQuestionActivity instance
     */
    GetQuestionActivity provideGetQuestionActivity();

    /**
     * Provides an instance of SaveUserAnswerActivity.
     *
     * @return The SaveUserAnswerActivity instance
     */
    SaveUserAnswerActivity provideSaveUserAnswerActivity();

    /**
     * Provides an instance of ViewHistoryActivity.
     *
     * @return The ViewHistoryActivity instance
     */
    ViewHistoryActivity provideViewHistoryActivity();

    /**
     * Provides an instance of DeleteAnswerActivity.
     *
     * @return The DeleteAnswerActivity instance
     */
    DeleteAnswerActivity provideDeleteAnswerActivity();
}
