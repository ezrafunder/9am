package com.nashss.se.nineam.activity.results;

import com.nashss.se.nineam.models.ScoreModel;

import java.util.List;

public class ViewScoreResult {
    private final List<ScoreModel> scores;

    public ViewScoreResult(List<ScoreModel> scores) {
        this.scores = scores;
    }
    public List<ScoreModel> getScores() {
        return this.scores;
    }

    @Override
    public String toString() {
        return "ViewScoreResult{" +
                "scores=" + scores +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ScoreModel> scores;
        public Builder setScores(List<ScoreModel> scores) {
            this.scores = scores;
            return this;
        }

        public ViewScoreResult build() {
            return new ViewScoreResult(scores);
        }
    }

}

//In here I need to return a list of a specific users answers. the answers will have the date on them already, and I need to use isCorrect. use getAllcontacts