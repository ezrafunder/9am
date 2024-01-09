package com.nashss.se.nineam.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "answers")
public class UserAnswer {
    @DynamoDBRangeKey(attributeName = "questionId")
    private String questionId;

    @DynamoDBHashKey(attributeName = "userId")
    private String userId;

    @DynamoDBAttribute(attributeName = "userChoice")
    private String userChoice;

    @DynamoDBAttribute(attributeName = "question")
    private String question;
    private String date;
    private boolean isCorrect;


    @Override
    public String toString() {
        return "UserAnswer{" +
                "questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                ", userChoice='" + userChoice + '\'' +
                ", date='" + date + '\'' +
                ", question='" + question + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return isCorrect == that.isCorrect && Objects.equals(questionId, that.questionId) && Objects.equals(userId, that.userId) && Objects.equals(userChoice, that.userChoice) && Objects.equals(date, that.date) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId, userChoice, date, question, isCorrect);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    @DynamoDBIndexHashKey(attributeName = "userId", globalSecondaryIndexName = "userIdAndIsCorrectIndex")
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
    @DynamoDBIndexRangeKey(attributeName = "isCorrect", globalSecondaryIndexName = "userIdAndIsCorrectIndex")
    public boolean isCorrect() {
        return isCorrect;
    }

}
