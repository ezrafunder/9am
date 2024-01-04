package com.nashss.se.nineam.activity.requests;

import com.nashss.se.nineam.models.AnswerModel;

public class ViewHistoryRequest {

    private String userId;

    private ViewHistoryRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }


        public ViewHistoryRequest build() {
            return new ViewHistoryRequest(userId);
        }
    }

}

