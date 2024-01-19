package com.nashss.se.nineam.activity.requests;

import java.util.Objects;

/**
 * Represents a request to get an answer.
 */
public class GetAnswerRequest {

    private final String questionId;
    private final String userId;

    /**
     * Constructs a GetAnswerRequest with the given question ID and user ID.
     *
     * @param questionId The ID of the question to get the answer for.
     * @param userId The ID of the user who answered the question.
     */
    public GetAnswerRequest(String questionId, String userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    /**
     * Returns the question ID associated with the get request.
     *
     * @return The question ID.
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Returns the user ID associated with the get request.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Determines whether this request is equal to another object.
     *
     * @param o The other object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAnswerRequest that = (GetAnswerRequest) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(userId, that.userId);
    }

    /**
     * Generates a hash code for this request.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId);
    }

    /**
     * Returns a string representation of this request.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "GetAnswerRequest{" +
                "questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    /**
     * Creates a builder for constructing GetAnswerRequest instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetAnswerRequest instances.
     */
    public static class Builder {
        private String questionId;
        private String userId;

        /**
         * Sets the question ID for the request being built.
         *
         * @param questionIdToSet The question ID to set.
         * @return The Builder instance for method chaining.
         */
        public Builder withQuestionId(String questionIdToSet) {
            this.questionId = questionIdToSet;
            return this;
        }

        /**
         * Sets the user ID for the request being built.
         *
         * @param userIdToSet The user ID to set.
         * @return The Builder instance for method chaining.
         */
        public Builder withUserId(String userIdToSet) {
            this.userId = userIdToSet;
            return this;
        }

        /**
         * Builds the GetAnswerRequest instance.
         *
         * @return The constructed GetAnswerRequest instance.
         */
        public GetAnswerRequest build() {
            return new GetAnswerRequest(questionId, userId);
        }
    }
}
