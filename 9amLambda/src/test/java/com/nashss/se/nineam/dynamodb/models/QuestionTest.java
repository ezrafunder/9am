package com.nashss.se.nineam.dynamodb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setDate("11-19-2023");
        question.setAnswer("42");
        question.setQuestionId("12345");

        Map<String, String> answerChoices = new HashMap<>();
        answerChoices.put("A", "Option A");
        answerChoices.put("B", "Option B");
        question.setAnswerChoices(answerChoices);

        question.setQuestion("Do you care about Taylor Swift and Travis?");
    }

    @Test
    void testDate() {
        assertEquals("11-19-2023", question.getDate());
    }

    @Test
    void testAnswer() {
        assertEquals("42", question.getAnswer());
    }

    @Test
    void testQuestionId() {
        assertEquals("12345", question.getQuestionId());
    }

    @Test
    void testAnswerChoices() {
        Map<String, String> expectedAnswerChoices = new HashMap<>();
        expectedAnswerChoices.put("A", "Option A");
        expectedAnswerChoices.put("B", "Option B");

        assertEquals(expectedAnswerChoices, question.getAnswerChoices());
    }

    @Test
    void testQuestion() {
        assertEquals("Do you care about Taylor Swift and Travis?", question.getQuestion());
    }

    @Test
    void testEquality() {
        Question sameQuestion = new Question();
        sameQuestion.setDate("11-19-2023");
        sameQuestion.setAnswer("42");
        sameQuestion.setQuestionId("12345");
        sameQuestion.setAnswerChoices(question.getAnswerChoices());
        sameQuestion.setQuestion("Do you care about Taylor Swift and Travis?");

        assertEquals(question, sameQuestion);
        assertNotEquals(question, null);
    }
}
