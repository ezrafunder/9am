package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import org.apache.commons.lang3.builder.Builder;

import java.util.List;

public class ViewHistoryResult {

    private List<AnswerModel> userAnswers;

    private ViewHistoryResult(List<AnswerModel> userAnswers) {
        this.userAnswers = userAnswers;
    }


    public List<AnswerModel> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<AnswerModel> userAnswers) {
        this.userAnswers = userAnswers;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<AnswerModel> answerModelList;

        public Builder withAnswerModelList(List<AnswerModel> answerModelList) {
            this.answerModelList = answerModelList;
            return this;
        }

        public ViewHistoryResult build() {
            return new ViewHistoryResult(answerModelList);
        }
    }
}







//In here I need to return a list of a specific users answers. the answers will have the date on them already, and I need to use isCorrect. use getAllcontacts








