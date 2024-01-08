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

    GetQuestionActivity provideGetQuestionActivity();

    SaveUserAnswerActivity provideSaveUserAnswerActivity();

    ViewHistoryActivity provideViewHistoryActivity();

    DeleteAnswerActivity provideDeleteAnswerActivity();
}
