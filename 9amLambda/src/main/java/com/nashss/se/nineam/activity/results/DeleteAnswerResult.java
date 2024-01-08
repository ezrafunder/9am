package com.nashss.se.nineam.activity.results;


public class DeleteAnswerResult {

    private final boolean success;

    private DeleteAnswerResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "DeleteAnswerResult{" +
                "success=" + success +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean success;

        public Builder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public DeleteAnswerResult build() {
            return new DeleteAnswerResult(success);
        }
    }
}

