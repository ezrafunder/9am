package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.ViewHistoryRequest;
import com.nashss.se.nineam.activity.results.ViewHistoryResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Activity for handling the viewing of a user's answer history.
 */
public class ViewHistoryActivity {

    private final UserAnswerDao userAnswerDao;

    /**
     * Constructs a new ViewHistoryActivity with the given UserAnswerDao.
     *
     * @param userAnswerDao The UserAnswerDao to use for retrieving user answers.
     */
    @Inject
    public ViewHistoryActivity(UserAnswerDao userAnswerDao) {
        this.userAnswerDao = userAnswerDao;
    }

    /**
     * Handles the request to view a user's answer history.
     *
     * @param getHistoryRequest The request object containing the user's ID and whether to show only correct answers.
     * @return The result containing the user's answer history.
     */
    public ViewHistoryResult handleRequest(final ViewHistoryRequest getHistoryRequest) {
        boolean correctOnly = getHistoryRequest.isCorrectOnly();

        List<UserAnswer> userAnswers = userAnswerDao.getAllUserAnswers(getHistoryRequest.getUserId(), correctOnly);
        List<AnswerModel> userAnswerModels = new ModelConverter().toAnswerModelList(userAnswers);

        return ViewHistoryResult.builder()
                .withAnswerModelList(userAnswerModels)
                .build();
    }
}

