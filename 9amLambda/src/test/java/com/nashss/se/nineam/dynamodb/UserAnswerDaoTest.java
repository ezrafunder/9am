package com.nashss.se.nineam.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.exceptions.UserAnswerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAnswerDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private UserAnswerDao userAnswerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserAnswer() {
        UserAnswer userAnswer = new UserAnswer();
        doNothing().when(dynamoDBMapper).save(userAnswer);

        assertDoesNotThrow(() -> userAnswerDao.saveUserAnswer(userAnswer));
        verify(dynamoDBMapper, times(1)).save(userAnswer);
    }

    @Test
    void getUserAnswer() {
        String userId = "testUserId";
        String questionId = "testQuestionId";
        UserAnswer expectedUserAnswer = new UserAnswer();
        when(dynamoDBMapper.load(UserAnswer.class, userId, questionId)).thenReturn(expectedUserAnswer);

        UserAnswer actualUserAnswer = userAnswerDao.getUserAnswer(userId, questionId);

        assertEquals(expectedUserAnswer, actualUserAnswer);
    }

    @Test
    void getUserAnswerNotFound() {
        String userId = "nonExistentUserId";
        String questionId = "nonExistentQuestionId";
        when(dynamoDBMapper.load(UserAnswer.class, userId, questionId)).thenReturn(null);

        assertThrows(UserAnswerNotFoundException.class, () -> userAnswerDao.getUserAnswer(userId, questionId));
    }


    @Test
    void deleteAnswer() {
        String userId = "testUserId";
        String questionId = "testQuestionId";
        UserAnswer userAnswer = new UserAnswer();
        when(dynamoDBMapper.load(UserAnswer.class, userId, questionId)).thenReturn(userAnswer);
        doNothing().when(dynamoDBMapper).delete(userAnswer);

        assertDoesNotThrow(() -> userAnswerDao.deleteAnswer(userId, questionId));
        verify(dynamoDBMapper, times(1)).delete(userAnswer);
    }
}
