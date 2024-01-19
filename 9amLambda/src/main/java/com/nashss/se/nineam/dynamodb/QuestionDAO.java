package com.nashss.se.nineam.dynamodb;

import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.exceptions.QuestionNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Data Access Object for accessing and managing Question data in DynamoDB.
 */
@Singleton
public class QuestionDAO {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Constructs a QuestionDAO with the provided DynamoDBMapper.
     *
     * @param dynamoDBMapper The DynamoDBMapper to use for data access.
     */
    @Inject
    public QuestionDAO(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Retrieves a Question from DynamoDB by its date.
     *
     * @param date The date of the question to retrieve.
     * @return The retrieved Question object.
     * @throws QuestionNotFoundException If a question with the specified date is not found.
     */
    public Question getQuestion(String date) throws QuestionNotFoundException {
        Question question = dynamoDBMapper.load(Question.class, date);
        if (null == question) {
            throw new QuestionNotFoundException(
                    String.format("Unable to find the question for : %s", date));
        }
        return question;
    }
}
