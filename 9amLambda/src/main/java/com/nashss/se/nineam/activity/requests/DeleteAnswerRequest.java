package com.nashss.se.nineam.activity.requests;

import java.util.Objects;

public class DeleteAnswerRequest {

    private final String questionId;
    private final String userId;

    private DeleteAnswerRequest(String questionId, String userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteAnswerRequest that = (DeleteAnswerRequest) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId);
    }

    @Override
    public String toString() {
        return "DeleteAnswerRequest{" +
                "questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String questionId;
        private String userId;

        public Builder withQuestionId(String questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public DeleteAnswerRequest build() {
            return new DeleteAnswerRequest(questionId, userId);
        }
    }
}

