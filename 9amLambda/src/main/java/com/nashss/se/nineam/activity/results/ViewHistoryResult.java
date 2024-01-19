package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.models.AnswerModel;

import org.apache.commons.lang3.builder.Builder;

import java.util.List;

/**
 * Represents the result of a view history operation.
 */
public class ViewHistoryResult {

    /**
     * The list of user answers.
     */
    private final List<AnswerModel> userAnswers;

    /**
     * Constructs a ViewHistoryResult with the given user answers.
     *
     * @param userAnswers The list of user answers.
     */
    private ViewHistoryResult(List<AnswerModel> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Returns the list of user answers.
     *
     * @return The list of user answers.
     */
    public List<AnswerModel> getUserAnswers() {
        return userAnswers;
    }

    /**
     * Creates a builder for constructing ViewHistoryResult instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ViewHistoryResult instances.
     */
    public static class Builder {
        private List<AnswerModel> answerModelsToSetForBuilding;

        /**
         * Sets the list of answer models for the result being built.
         *
         * @param answerModelsToSet The list of answer models to set.
         * @return The Builder instance for method chaining.
         */
        public Builder withAnswerModelList(List<AnswerModel> answerModelsToSet) {
            this.answerModelsToSetForBuilding = answerModelsToSet;
            return this;
        }

        /**
         * Builds the ViewHistoryResult instance.
         *
         * @return The constructed ViewHistoryResult instance.
         */
        public ViewHistoryResult build() {
            return new ViewHistoryResult(answerModelsToSetForBuilding);
        }
    }
}
