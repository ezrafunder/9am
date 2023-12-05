package com.nashss.se.nineam.converters;

import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.models.QuestionModel;




/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    public QuestionModel toQuestionModel(Question question) {
        return QuestionModel.builder()
                .withQuestion(question.getQuestion())
                .withDate(question.getDate())
                .withQuestionId(question.getQuestionId())
                .withAnswer(question.getAnswer())
                .withAnswerChoices(question.getAnswerChoices())
                .build();
    }


}

