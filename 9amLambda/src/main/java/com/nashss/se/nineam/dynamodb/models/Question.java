package com.nashss.se.nineam.dynamodb.models;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Map;
import java.util.Objects;
@DynamoDBTable(tableName = "questions")
public class Question {
    private String date;
    private String answer;
    private Integer questionId;
    private Map<String, String> answerChoices;
    private String question;


@DynamoDBHashKey(attributeName = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @DynamoDBAttribute(attributeName = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @DynamoDBAttribute(attributeName = "questionId")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    @DynamoDBAttribute(attributeName = "answerChoices")
    public Map<String, String> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(Map<String, String> answerChoices) {
        this.answerChoices = answerChoices;
    }
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(date, question1.date) && Objects.equals(answer, question1.answer) && Objects.equals(questionId, question1.questionId) && Objects.equals(answerChoices, question1.answerChoices) && Objects.equals(question, question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, answer, questionId, answerChoices, question);
    }

    @Override
    public String toString() {
        return "Question{" +
                "date='" + date + '\'' +
                ", answer='" + answer + '\'' +
                ", questionId=" + questionId +
                ", answerChoices=" + answerChoices +
                ", question='" + question + '\'' +
                '}';
    }
}
