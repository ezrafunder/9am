package com.nashss.se.nineam.converters;

import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import com.nashss.se.nineam.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a Question object to a QuestionModel object.
     *
     * @param question The Question object to convert.
     * @return The converted QuestionModel object.
     */
    public QuestionModel toQuestionModel(Question question) {
        return QuestionModel.builder()
                .withQuestion(question.getQuestion())
                .withDate(question.getDate())
                .withQuestionId(question.getQuestionId())
                .withAnswer(question.getAnswer())
                .withAnswerChoices(question.getAnswerChoices())
                .build();
    }

    /**
     * Converts a UserAnswer object to an AnswerModel object.
     *
     * @param userAnswer The UserAnswer object to convert.
     * @return The converted AnswerModel object.
     */
    public AnswerModel toAnswerModel(UserAnswer userAnswer) {
        return AnswerModel.builder()
                .withQuestionId(userAnswer.getQuestionId())
                .withIsCorrect(userAnswer.isCorrect())
                .withUserChoice(userAnswer.getUserChoice())
                .withUserId(userAnswer.getUserId())
                .withDate(userAnswer.getDate())
                .withQuestion(userAnswer.getQuestion())
                .build();
    }

    /**
     * Converts a list of UserAnswer objects to a list of AnswerModel objects.
     *
     * @param answers The list of UserAnswer objects to convert.
     * @return The list of converted AnswerModel objects.
     */
    public List<AnswerModel> toAnswerModelList(List<UserAnswer> answers) {
        List<AnswerModel> answerModels = new ArrayList<>();

        for (UserAnswer answer : answers) {
            answerModels.add(toAnswerModel(answer));
        }

        return answerModels;
    }
}
