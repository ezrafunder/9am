package com.nashss.se.nineam.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionModelTest {

    @Test
    void testEqualsAndHashCode() {
        Map<String, String> answerChoices1 = new HashMap<>();
        answerChoices1.put("A", "Choice A");
        answerChoices1.put("B", "Choice B");

        Map<String, String> answerChoices2 = new HashMap<>();
        answerChoices2.put("A", "Choice A");
        answerChoices2.put("B", "Choice B");

        QuestionModel question1 = QuestionModel.builder()
                .withDate("11-19-2023")
                .withAnswer("Yes")
                .withQuestionId("1")
                .withAnswerChoices(answerChoices1)
                .withQuestion("Do you care about Taylor Swift and Travis?")
                .build();

        QuestionModel question2 = QuestionModel.builder()
                .withDate("11-19-2023")
                .withAnswer("Yes")
                .withQuestionId("1")
                .withAnswerChoices(answerChoices2)
                .withQuestion("Do you care about Taylor Swift and Travis?")
                .build();

        QuestionModel question3 = QuestionModel.builder()
                .withDate("11-19-2023")
                .withAnswer("No")
                .withQuestionId("2")
                .withAnswerChoices(new HashMap<>())
                .withQuestion("What's your favorite color?")
                .build();

        assertEquals(question1, question2);
        assertEquals(question1.hashCode(), question2.hashCode());

        assertNotEquals(question1, question3);
        assertNotEquals(question1.hashCode(), question3.hashCode());
    }

    @Test
    void testBuilder() {
        Map<String, String> answerChoices = new HashMap<>();
        answerChoices.put("A", "Choice A");
        answerChoices.put("B", "Choice B");

        QuestionModel question = QuestionModel.builder()
                .withDate("11-19-2023")
                .withAnswer("Yes")
                .withQuestionId("1")
                .withAnswerChoices(answerChoices)
                .withQuestion("Do you care about Taylor Swift and Travis?")
                .build();

        assertEquals("11-19-2023", question.getDate());
        assertEquals("Yes", question.getAnswer());
        assertEquals("1", question.getQuestionId());
        assertEquals(answerChoices, question.getAnswerChoices());
        assertEquals("Do you care about Taylor Swift and Travis?", question.getQuestion());
    }
}
