package com.nashss.se.nineam.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = SaveUserAnswerRequest.Builder.class)
public class SaveUserAnswerRequest {
//checkstyle messed up my code here
    private String date;
    private String userChoice;
    private String userId;

    public SaveUserAnswerRequest(String date, String userChoice, String userId) {
        this.date = date;
        this.userChoice = userChoice;
        this.userId = userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String date;
        private String userChoice;
        private String userId;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withUserChoice(String userChoice) {
            this.userChoice = userChoice;
            return this;
        }
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public SaveUserAnswerRequest build() {
            return new SaveUserAnswerRequest(date, userChoice, userId);
        }
    }
}