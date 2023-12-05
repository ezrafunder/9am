package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.GetQuestionRequest;
import com.nashss.se.nineam.activity.results.GetQuestionResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.QuestionDAO;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.models.QuestionModel;

import javax.inject.Inject;

public class GetQuestionActivity {
    private final QuestionDAO questionDAO;
    @Inject
    public GetQuestionActivity(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
    public GetQuestionResult handleRequest(final GetQuestionRequest getQuestionRequest) {
        Question question = questionDAO.getQuestion(getQuestionRequest.getDate());
        QuestionModel questionModel = new ModelConverter().toQuestionModel(question);
        return GetQuestionResult.builder()
                .withQuestion(questionModel)
                .build();
    }
}
