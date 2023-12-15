package com.nashss.se.nineam.dynamodb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserAnswerTest {

    private UserAnswer userAnswer;

    @BeforeEach
    void setUp() {
        userAnswer = new UserAnswer();
        userAnswer.setQuestionId("12");
        userAnswer.setUserId("11");
        userAnswer.setUserChoice("Option A");
        userAnswer.setCorrect(true);
    }

    @Test
    void testQuestionId() {
        assertEquals("12", userAnswer.getQuestionId());
    }

    @Test
    void testUserId() {
        assertEquals("11", userAnswer.getUserId());
    }

    @Test
    void testUserChoice() {
        assertEquals("Option A", userAnswer.getUserChoice());
    }

    @Test
    void testIsCorrect() {
        assertEquals(true, userAnswer.isCorrect());
    }

    @Test
    void testEquality() {
        UserAnswer sameUserAnswer = new UserAnswer();
        sameUserAnswer.setQuestionId("12");
        sameUserAnswer.setUserId("11");
        sameUserAnswer.setUserChoice("Option A");
        sameUserAnswer.setCorrect(true);

        assertEquals(userAnswer, sameUserAnswer);
        assertNotEquals(userAnswer, null);
    }
}
