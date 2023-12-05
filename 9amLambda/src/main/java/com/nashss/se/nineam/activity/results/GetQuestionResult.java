package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.models.QuestionModel;

public class GetQuestionResult {

    private final QuestionModel question;

    private GetQuestionResult(QuestionModel question) {
        this.question = question;
    }

    public QuestionModel getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "GetQuestionResult{" +
                "question=" + question +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private QuestionModel question;
        public Builder withQuestion(QuestionModel question) {
            this.question = question;
            return this;
        }
        public GetQuestionResult build() {
            return new GetQuestionResult(question);
        }
    }

}
