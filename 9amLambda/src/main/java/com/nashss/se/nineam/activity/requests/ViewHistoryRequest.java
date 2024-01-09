package com.nashss.se.nineam.activity.requests;

import com.nashss.se.nineam.models.AnswerModel;

public class ViewHistoryRequest {

    private String userId;
    private boolean correctOnly;

    private ViewHistoryRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isCorrectOnly() {
        return correctOnly;
    }

    public void setCorrectOnly(boolean correctOnly) {
        this.correctOnly = correctOnly;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private boolean correctOnly;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withCorrectOnly(boolean correctOnly) {
            this.correctOnly = correctOnly;
            return this;
        }

        public ViewHistoryRequest build() {
            ViewHistoryRequest request = new ViewHistoryRequest(userId);
            request.setCorrectOnly(correctOnly);
            return request;
        }
    }
}
