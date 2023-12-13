package com.nashss.se.nineam.activity.results;

import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import com.nashss.se.nineam.models.AnswerModel;
public class SaveUserAnswerResult {

    private AnswerModel answer;

    public AnswerModel getAnswer() {
        return answer;
    }

    private SaveUserAnswerResult(AnswerModel answer) {
        this.answer = answer;
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
