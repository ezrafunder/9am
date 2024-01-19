package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.models.QuestionModel;

/**
 * Represents the result of a get question operation.
 */
public class GetQuestionResult {

    /**
     * The retrieved question.
     */
    private final QuestionModel question;

    /**
     * Constructs a GetQuestionResult with the given question.
     *
     * @param question The retrieved question.
     */
    private GetQuestionResult(QuestionModel question) {
        this.question = question;
    }

    /**
     * Returns the retrieved question.
     *
     * @return The retrieved question.
     */
    public QuestionModel getQuestion() {
        return question;
    }

    // ... (toString method remains the same)

    /**
     * Creates a builder for constructing GetQuestionResult instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetQuestionResult instances.
     */
    public static class Builder {
        private QuestionModel question;

        /**
         * Sets the question for the result being built.
         *
         * @param newQuestion The retrieved question.
         * @return The Builder instance for method chaining.
         */
        public Builder withQuestion(QuestionModel newQuestion) {
            this.question = newQuestion;
            return this;
        }

        /**
         * Builds the GetQuestionResult instance.
         *
         * @return The constructed GetQuestionResult instance.
         */
        public GetQuestionResult build() {
            return new GetQuestionResult(question);
        }
    }
}
