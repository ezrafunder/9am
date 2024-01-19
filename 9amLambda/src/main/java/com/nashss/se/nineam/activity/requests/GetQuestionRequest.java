package com.nashss.se.nineam.activity.requests;

import java.util.Objects;

/**
 * Represents a request to get a question.
 */
public class GetQuestionRequest {

    private final String date;

    /**
     * Constructs a GetQuestionRequest with the given date.
     *
     * @param date The date for which to get the question.
     */
    public GetQuestionRequest(String date) {
        this.date = date;
    }

    /**
     * Returns the date associated with the get request.
     *
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Determines whether this request is equal to another object.
     *
     * @param o The other object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetQuestionRequest that = (GetQuestionRequest) o;
        return Objects.equals(date, that.date);
    }

    /**
     * Generates a hash code for this request.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    /**
     * Returns a string representation of this request.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "GetQuestionRequest{" +
                "date='" + date + '\'' +
                '}';
    }

    /**
     * Creates a builder for constructing GetQuestionRequest instances.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetQuestionRequest instances.
     */
    public static class Builder {
        private String date;

        /**
         * Sets the date for the request being built.
         *
         * @param dateToSet The date to set.
         * @return The Builder instance for method chaining.
         */
        public Builder withDate(String dateToSet) {
            this.date = dateToSet;
            return this;
        }

        /**
         * Builds the GetQuestionRequest instance.
         *
         * @return The constructed GetQuestionRequest instance.
         */
        public GetQuestionRequest build() {
            return new GetQuestionRequest(date);
        }
    }
}
