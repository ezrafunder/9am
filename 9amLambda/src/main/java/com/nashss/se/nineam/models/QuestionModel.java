package com.nashss.se.nineam.models;

import com.nashss.se.nineam.dynamodb.models.Question;

import java.util.Map;
import java.util.Objects;

public class QuestionModel {
    private String date;
    private String answer;
    private Integer questionId;
    private Map<String, String> answerChoices;
    private String question;

    private QuestionModel(String date, String answer, Integer questionId, Map<String, String> answerChoices, String question) {
        this.date = date;
        this.answer = answer;
        this.questionId = questionId;
        this.answerChoices = answerChoices;
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Map<String, String> getAnswerChoices() {
        return answerChoices;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionModel that = (QuestionModel) o;
        return Objects.equals(date, that.date) && Objects.equals(answer, that.answer) && Objects.equals(questionId, that.questionId) && Objects.equals(answerChoices, that.answerChoices) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, answer, questionId, answerChoices, question);
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String date;
        private String answer;
        private Integer questionId;
        private Map<String, String> answerChoices;
        private String question;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }
        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }
        public Builder withAnswerChoices(Map<String, String> answerChoices ) {
            this.answerChoices = answerChoices;
            return this;
        }
        public Builder withQuestionId(Integer questionId) {
            this.questionId = questionId;
            return this;
        }
        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }
        public QuestionModel build() {
            return new QuestionModel(date, answer, questionId, answerChoices, question );
        }

    }
}
