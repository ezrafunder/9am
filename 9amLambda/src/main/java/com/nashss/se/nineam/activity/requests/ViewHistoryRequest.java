package com.nashss.se.nineam.activity.requests;

/**
 * Represents a request to view a user's history of answers.
 */
public class ViewHistoryRequest {

    private final String userId;
    private boolean correctOnly;

    /**
     * Constructs a ViewHistoryRequest with the given user ID.
     *
     * @param userId The ID of the user whose history is being viewed.
     */
    private ViewHistoryRequest(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the user ID associated with the view history request.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns whether the request should only include correct answers.
     *
     * @return True if only correct answers should be included, false otherwise.
     */
    public boolean isCorrectOnly() {
        return correctOnly;
    }

    /**
     * Creates a builder for constructing ViewHistoryRequest instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ViewHistoryRequest instances.
     */
    public static class Builder {
        private String userId;
        private boolean correctOnly;

        /**
         * Sets the user ID for the request being built.
         *
         * @param newUserId The user ID to set.
         * @return The Builder instance for method chaining.
         */
        public Builder withUserId(String newUserId) {
            this.userId = newUserId;
            return this;
        }

        /**
         * Sets whether the request should only include correct answers.
         *
         * @param newCorrectOnly True if only correct answers should be included, false otherwise.
         * @return The Builder instance for method chaining.
         */
        public Builder withCorrectOnly(boolean newCorrectOnly) {
            this.correctOnly = newCorrectOnly;
            return this;
        }

        /**
         * Builds the ViewHistoryRequest instance.
         *
         * @return The constructed ViewHistoryRequest instance.
         */
        public ViewHistoryRequest build() {
            ViewHistoryRequest request = new ViewHistoryRequest(userId);
            request.correctOnly = correctOnly;
            return request;
        }
    }
}
