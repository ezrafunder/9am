package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.DeleteAnswerRequest;
import com.nashss.se.nineam.activity.results.DeleteAnswerResult;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;

import javax.inject.Inject;

public class DeleteAnswerActivity {

    private final UserAnswerDao userAnswerDao;

    @Inject
    public DeleteAnswerActivity(UserAnswerDao userAnswerDao) {
        this.userAnswerDao = userAnswerDao;
    }

    public DeleteAnswerResult handleRequest(DeleteAnswerRequest request) {
        String userId = request.getUserId();
        String questionId = request.getQuestionId();

        try {
            userAnswerDao.deleteAnswer(userId, questionId);
            return DeleteAnswerResult.builder().withSuccess(true).build();
        } catch (Exception e) {
            return DeleteAnswerResult.builder().withSuccess(false).build();
        }
    }
}

