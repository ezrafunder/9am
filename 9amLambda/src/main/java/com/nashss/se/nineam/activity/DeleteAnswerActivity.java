package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.DeleteAnswerRequest;
import com.nashss.se.nineam.activity.results.DeleteAnswerResult;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;

import javax.inject.Inject;

/**
 * Activity for handling the deletion of an answer.
 */
public class DeleteAnswerActivity {

    private final UserAnswerDao userAnswerDao;

    /**
     * Constructs a new DeleteAnswerActivity with the given UserAnswerDao.
     *
     * @param userAnswerDao The UserAnswerDao to use for answer deletion.
     */
    @Inject
    public DeleteAnswerActivity(UserAnswerDao userAnswerDao) {
        this.userAnswerDao = userAnswerDao;
    }

    /**
     * Handles the request to delete an answer.
     *
     * @param request The request object containing userId and questionId.
     * @return The result of the delete operation.
     */
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
