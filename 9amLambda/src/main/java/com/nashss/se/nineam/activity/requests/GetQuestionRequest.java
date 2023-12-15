package com.nashss.se.nineam.activity.requests;

import java.util.Objects;

public class GetQuestionRequest {

    private final String date;

    public GetQuestionRequest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetQuestionRequest that = (GetQuestionRequest) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "GetQuestionRequest{" +
                "date='" + date + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String date;
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }


        public GetQuestionRequest build() {
            return new GetQuestionRequest(date);
        }

    }

}
