package com.nashss.se.nineam.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerModelTest {

    @Test
    void testEqualsAndHashCode() {
        AnswerModel answer1 = AnswerModel.builder()
                .withQuestionId("1")
                .withUserId("user1")
                .withUserChoice("choiceA")
                .withIsCorrect(true)
                .build();

        AnswerModel answer2 = AnswerModel.builder()
                .withQuestionId("1")
                .withUserId("user1")
                .withUserChoice("choiceA")
                .withIsCorrect(true)
                .build();

        AnswerModel answer3 = AnswerModel.builder()
                .withQuestionId("2")
                .withUserId("user2")
                .withUserChoice("choiceB")
                .withIsCorrect(false)
                .build();

        // Test equality
        assertEquals(answer1, answer2);
        assertEquals(answer1.hashCode(), answer2.hashCode());

        // Test non-equality
        assertNotEquals(answer1, answer3);
        assertNotEquals(answer1.hashCode(), answer3.hashCode());
    }

    @Test
    void testBuilder() {
        AnswerModel answer = AnswerModel.builder()
                .withQuestionId("1")
                .withUserId("user1")
                .withUserChoice("choiceA")
                .withIsCorrect(true)
                .build();

        assertEquals("1", answer.getQuestionId());
        assertEquals("user1", answer.getUserId());
        assertEquals("choiceA", answer.getUserChoice());
        assertTrue(answer.isCorrect());
    }
}
