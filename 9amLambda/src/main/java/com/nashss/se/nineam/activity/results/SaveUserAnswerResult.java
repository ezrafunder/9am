package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.models.AnswerModel;

/**
 * Represents the result of a save user answer operation.
 */
public class SaveUserAnswerResult {

    /**
     * The saved answer.
     */
    private final AnswerModel answer;

    /**
     * Constructs a SaveUserAnswerResult with the given answer.
     *
     * @param answer The saved answer.
     */
    private SaveUserAnswerResult(AnswerModel answer) {
        this.answer = answer;
    }

    /**
     * Returns the saved answer.
     *
     * @return The saved answer.
     */
    public AnswerModel getAnswer() {
        return answer;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AnswerModel answerModel;

        public Builder withAnswer(AnswerModel answerModel) {
            this.answerModel = answerModel;
            return this;
        }

        public SaveUserAnswerResult build() {
            return new SaveUserAnswerResult(answerModel);
        }
    }

}
