package com.nashss.se.nineam.models;

import java.util.Objects;

public class ScoreModel {

    private final String userId;
    private final int userScore;

    private ScoreModel(String userId, int userScore) {
        this.userId = userId;
        this.userScore = userScore;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserScore() {
        return userScore;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private int userScore;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUserScore(int userScore) {
            this.userScore = userScore;
            return this;
        }

        public ScoreModel build() {
            return new ScoreModel(userId, userScore);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreModel that = (ScoreModel) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userScore, that.userScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userScore);
    }
}

