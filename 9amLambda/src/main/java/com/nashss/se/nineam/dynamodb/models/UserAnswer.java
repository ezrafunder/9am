package com.nashss.se.nineam.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "answers")
public class UserAnswer {

    @DynamoDBRangeKey(attributeName = "questionId")
    private String questionId;

    @DynamoDBHashKey(attributeName = "userId")
    private String userId;

    @DynamoDBAttribute(attributeName = "userChoice")
    private String userChoice;

    @DynamoDBAttribute(attributeName = "isCorrect")
    private boolean isCorrect;

    public UserAnswer() {
        this.questionId = questionId;
        this.userId = userId;
        this.userChoice = userChoice;
        this.isCorrect = isCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

//    public int getScore() {
//        return isCorrect() ? 1 : 0;
//    }
//
//    public String getFeedback() {
//        if (isCorrect()) {
//            return "Correct!";
//        } else {
//            return "Incorrect.";
//        }
//    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                ", userChoice='" + userChoice + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return isCorrect == that.isCorrect && Objects.equals(questionId, that.questionId) && Objects.equals(userId, that.userId) && Objects.equals(userChoice, that.userChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId, userChoice, isCorrect);
    }
}
