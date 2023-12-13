package com.nashss.se.nineam.models;

import com.nashss.se.nineam.activity.requests.GetAnswerRequest;

import java.util.Objects;

public class AnswerModel {

    private final String questionId;
    private final String userId;
    private final String userChoice;
    private final boolean isCorrect;

    private AnswerModel(String questionId, String userId, String userChoice, boolean isCorrect) {
        this.questionId = questionId;
        this.userId = userId;
        this.userChoice = userChoice;
        this.isCorrect = isCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerModel that = (AnswerModel) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(isCorrect, that.isCorrect) && Objects.equals(userChoice, that.userChoice) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId, userChoice, isCorrect);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String questionId;
        private String userId;
        private String userChoice;
        private boolean isCorrect;

        public Builder withQuestionId(String questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUserChoice(String userChoice) {
            this.userChoice = userChoice;
            return this;
        }

        public Builder withIsCorrect(boolean isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public AnswerModel build() {
            return new AnswerModel(questionId, userId, userChoice, isCorrect);
        }
    }
}

