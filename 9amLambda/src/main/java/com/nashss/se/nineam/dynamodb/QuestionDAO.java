package com.nashss.se.nineam.dynamodb;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.exceptions.QuestionNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
public class QuestionDAO {
    private  final DynamoDBMapper dynamoDBMapper;
    @Inject
   public QuestionDAO(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Question getQuestion(String date) {
        Question question = dynamoDBMapper.load(Question.class, date);
        if (null == question) {
            throw new QuestionNotFoundException(
                    String.format("Unable to find the question for : %s", date));
        }

        return question;
    }

}
