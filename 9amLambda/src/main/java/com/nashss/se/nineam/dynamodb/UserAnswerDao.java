package com.nashss.se.nineam.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.exceptions.UserAnswerNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton

public

class UserAnswerDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserAnswerDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void saveUserAnswer(UserAnswer userAnswer) {
        dynamoDBMapper.save(userAnswer);
    }

    public UserAnswer getUserAnswer(String userId, String questionId) {
        UserAnswer userAnswer = dynamoDBMapper.load(UserAnswer.class, userId, questionId);
        if (userAnswer == null) {
            throw new UserAnswerNotFoundException(
                    String.format("Unable to find user answer for userId %s and questionId %s", userId, questionId));
        }
        return userAnswer;
    }

    public List<UserAnswer> getAllUserAnswers(String userId, boolean correctOnly) {
        DynamoDBQueryExpression<UserAnswer> queryExpression;

        if (correctOnly) {
            queryExpression = new DynamoDBQueryExpression<UserAnswer>()
                    .withIndexName("userIdAndIsCorrectIndex") // GSI
                    .withConsistentRead(false)
                    .withKeyConditionExpression("userId = :userId and isCorrect = :isCorrect")
                    .withExpressionAttributeValues(Map.of(
                            ":userId", new AttributeValue().withS(userId),
                            ":isCorrect", new AttributeValue().withS("true")
                    ));
        } else {
            queryExpression = new DynamoDBQueryExpression<UserAnswer>()
                    .withKeyConditionExpression("userId = :userId")
                    .withExpressionAttributeValues(Map.of(
                            ":userId", new AttributeValue().withS(userId)
                    ));
        }

        return dynamoDBMapper.query(UserAnswer.class, queryExpression);
    }




    public void updateScore(UserAnswer userAnswer) {
        dynamoDBMapper.save(userAnswer);
    }

    public void deleteAnswer(String userId, String questionId) {
        UserAnswer userAnswer = getUserAnswer(userId, questionId);
        dynamoDBMapper.delete(userAnswer);
    }
}




