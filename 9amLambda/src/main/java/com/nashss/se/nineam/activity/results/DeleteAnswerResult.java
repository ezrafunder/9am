package com.nashss.se.nineam.activity.results;

/**
 * Represents the result of a delete answer operation.
 */
public class DeleteAnswerResult {

    /**
     * Indicates whether the answer was deleted successfully.
     */
    private final boolean success;

    /**
     * Constructs a DeleteAnswerResult with the given success status.
     *
     * @param success True if the answer was deleted successfully, false otherwise.
     */
    private DeleteAnswerResult(boolean success) {
        this.success = success;
    }

    /**
     * Returns whether the answer was deleted successfully.
     *
     * @return True if the answer was deleted successfully, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    // ... (toString method remains the same)

    /**
     * Creates a builder for constructing DeleteAnswerResult instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing DeleteAnswerResult instances.
     */
    public static class Builder {
        private boolean success;

        /**
         * Sets the success status for the result being built.
         *
         * @param newSuccess True if the answer was deleted successfully, false otherwise.
         * @return The Builder instance for method chaining.
         */
        public Builder withSuccess(boolean newSuccess) {
            this.success = newSuccess;
            return this;
        }

        /**
         * Builds the DeleteAnswerResult instance.
         *
         * @return The constructed DeleteAnswerResult instance.
         */
        public DeleteAnswerResult build() {
            return new DeleteAnswerResult(success);
        }
    }
}
