package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.GetQuestionRequest;
import com.nashss.se.nineam.activity.results.GetQuestionResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.QuestionDAO;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.models.QuestionModel;

import javax.inject.Inject;

/**
 * Activity for handling the retrieval of a question.
 */
public class GetQuestionActivity {

    private final QuestionDAO questionDAO;

    /**
     * Constructs a new GetQuestionActivity with the given QuestionDAO.
     *
     * @param questionDAO The QuestionDAO to use for question retrieval.
     */
    @Inject
    public GetQuestionActivity(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    /**
     * Handles the request to get a question.
     *
     * @param getQuestionRequest The request object containing the date of the question to retrieve.
     * @return The result containing the retrieved question.
     */
    public GetQuestionResult handleRequest(final GetQuestionRequest getQuestionRequest) {
        Question question = questionDAO.getQuestion(getQuestionRequest.getDate());
        QuestionModel questionModel = new ModelConverter().toQuestionModel(question);
        return GetQuestionResult.builder()
                .withQuestion(questionModel)
                .build();
    }
}
