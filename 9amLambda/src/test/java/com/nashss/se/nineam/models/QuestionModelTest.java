package com.nashss.se.nineam.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionModelTest {

    @Test
    public void testGetAnswer() {
        QuestionModel questionModel = QuestionModel.builder()
                .withDate("2024-01-19")
                .withAnswer("42")
                .withQuestionId("Q123")
                .withAnswerChoices(createSampleAnswerChoices())
                .withQuestion("What is the meaning of life?")
                .build();

        String expectedAnswer = "42";
        String actualAnswer = questionModel.getAnswer();
        assertEquals(expectedAnswer, actualAnswer);
    }
    private Map<String, String> createSampleAnswerChoices() {
        Map<String, String> answerChoices = new HashMap<>();
        answerChoices.put("A", "To be happy");
        answerChoices.put("B", "To learn and grow");
        answerChoices.put("C", "42");
        answerChoices.put("D", "To love and be loved");
        return answerChoices;
    }
}
