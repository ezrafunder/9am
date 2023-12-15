package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.SaveUserAnswerRequest;
import com.nashss.se.nineam.activity.results.SaveUserAnswerResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.QuestionDAO;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

public class SaveUserAnswerActivityTest {

    @Mock
    private QuestionDAO questionDao;

    @Mock
    private UserAnswerDao userAnswerDao;

    @InjectMocks
    private SaveUserAnswerActivity saveUserAnswerActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_validRequest_savesUserAnswerAndReturnsResult() {
        String testDate = "2023-01-01";
        String testUserChoice = "A";
        String testUserId = "ezra";

        Question testQuestion = new Question();
        testQuestion.setDate(testDate);
        testQuestion.setAnswer("A");

        when(questionDao.getQuestion(testDate)).thenReturn(testQuestion);

        SaveUserAnswerRequest request = new SaveUserAnswerRequest(testDate, testUserChoice, testUserId);

        SaveUserAnswerResult result = saveUserAnswerActivity.handleRequest(request);

        verify(questionDao).getQuestion(testDate);

        UserAnswer expectedUserAnswer = new UserAnswer();
        expectedUserAnswer.setUserId(testUserId);
        expectedUserAnswer.setUserChoice(testUserChoice);
        expectedUserAnswer.setQuestionId(testQuestion.getQuestionId());
        expectedUserAnswer.setCorrect(testUserChoice.equals(testQuestion.getAnswer()));
        verify(userAnswerDao).saveUserAnswer(expectedUserAnswer);

        AnswerModel expectedAnswerModel = new ModelConverter().toAnswerModel(expectedUserAnswer);
        assertEquals(expectedAnswerModel, result.getAnswer());
    }
}
