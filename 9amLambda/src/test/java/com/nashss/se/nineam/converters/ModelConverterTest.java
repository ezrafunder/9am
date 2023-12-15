package com.nashss.se.nineam.converters;

import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import com.nashss.se.nineam.models.QuestionModel;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelConverterTest {

    @Test
    void toQuestionModel_validQuestion_returnsQuestionModel() {
        // Given
        ModelConverter modelConverter = new ModelConverter();
        Question question = new Question();
        question.setQuestion("Do you care about Taylor Swift and Travis?");
        question.setDate("2023-11-19");
        question.setQuestionId("12345");
        question.setAnswer("no");

        Map<String, String> answerChoices = new HashMap<>();

        answerChoices.put("A", "no");
        answerChoices.put("B", "yes");

        question.setAnswerChoices(answerChoices);
        // When
        QuestionModel result = modelConverter.toQuestionModel(question);
        // Then
        assertEquals(question.getQuestion(), result.getQuestion());
        assertEquals(question.getDate(), result.getDate());
        assertEquals(question.getQuestionId(), result.getQuestionId());
        assertEquals(question.getAnswer(), result.getAnswer());
        assertEquals(question.getAnswerChoices(), result.getAnswerChoices());
    }

    @Test
    void toAnswerModel_validUserAnswer_returnsAnswerModel() {
        // Given
        ModelConverter modelConverter = new ModelConverter();
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserId("ezra");
        userAnswer.setUserChoice("A");
        userAnswer.setQuestionId("42");
        userAnswer.setCorrect(true);
        // When
        AnswerModel result = modelConverter.toAnswerModel(userAnswer);
        // Then
        assertEquals(userAnswer.getUserId(), result.getUserId());
        assertEquals(userAnswer.getUserChoice(), result.getUserChoice());
        assertEquals(userAnswer.getQuestionId(), result.getQuestionId());
        assertEquals(userAnswer.isCorrect(), result.isCorrect());
    }


}
