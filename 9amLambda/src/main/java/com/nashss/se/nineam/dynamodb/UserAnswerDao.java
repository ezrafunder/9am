package com.nashss.se.nineam.dynamodb;

import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.exceptions.UserAnswerNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/**
 * Data Access Object for managing UserAnswer data in DynamoDB.
 */
@Singleton
public class UserAnswerDao {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Constructs a UserAnswerDao with the provided DynamoDBMapper.
     *
     * @param dynamoDBMapper The DynamoDBMapper to use for data access.
     */
    @Inject
    public UserAnswerDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Saves a UserAnswer to DynamoDB.
     *
     * @param userAnswer The UserAnswer to save.
     */
    public void saveUserAnswer(UserAnswer userAnswer) {
        dynamoDBMapper.save(userAnswer);
    }

    /**
     * Retrieves a UserAnswer from DynamoDB by userId and questionId.
     *
     * @param userId    The user ID.
     * @param questionId The question ID.
     * @return The UserAnswer.
     * @throws UserAnswerNotFoundException If the UserAnswer is not found.
     */
    public UserAnswer getUserAnswer(String userId, String questionId) {
        UserAnswer userAnswer = dynamoDBMapper.load(UserAnswer.class, userId, questionId);
        if (userAnswer == null) {
            throw new UserAnswerNotFoundException(
                    String.format("Unable to find user answer for userId %s and questionId %s", userId, questionId));
        }
        return userAnswer;
    }

    /**
     * Retrieves a list of UserAnswers from DynamoDB for a given userId, optionally filtering for correct answers only.
     *
     * @param userId       The user ID.
     * @param correctOnly  Whether to filter for correct answers only.
     * @return A list of UserAnswers.
     */
    public List<UserAnswer> getAllUserAnswers(String userId, boolean correctOnly) {
        DynamoDBQueryExpression<UserAnswer> queryExpression;

        if (correctOnly) {
            queryExpression = new DynamoDBQueryExpression<UserAnswer>()
                    .withIndexName("userIdAndIsCorrectIndex")
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

    /**
     * Updates the score of a UserAnswer in DynamoDB.
     *
     * @param userAnswer The UserAnswer with the updated score.
     */
    public void updateScore(UserAnswer userAnswer) {
        dynamoDBMapper.save(userAnswer);
    }

    /**
     * Deletes a UserAnswer from DynamoDB.
     *
     * @param userId    The user ID.
     * @param questionId The question ID.
     */
    public void deleteAnswer(String userId, String questionId) {
        UserAnswer userAnswer = getUserAnswer(userId, questionId);
        dynamoDBMapper.delete(userAnswer);
    }
}
