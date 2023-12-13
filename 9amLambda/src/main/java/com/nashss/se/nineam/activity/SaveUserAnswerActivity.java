package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.SaveUserAnswerRequest;
import com.nashss.se.nineam.activity.results.SaveUserAnswerResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.QuestionDAO;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import javax.inject.Inject;


public class SaveUserAnswerActivity {

    private final QuestionDAO questionDao;
    private final UserAnswerDao userAnswerDao;

    @Inject
    public SaveUserAnswerActivity(QuestionDAO questionDao, UserAnswerDao userAnswerDao) {
        this.questionDao = questionDao;
        this.userAnswerDao = userAnswerDao;
    }

    public SaveUserAnswerResult handleRequest(SaveUserAnswerRequest request) {
        String date = request.getDate();
        String userChoice = request.getUserChoice();
        String userId = request.getUserId();

        Question question = questionDao.getQuestion(date);

        boolean isCorrect = userChoice.equals(question.getAnswer());

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserId(request.getUserId());
        userAnswer.setUserChoice(request.getUserChoice());
        userAnswer.setQuestionId(question.getQuestionId());
        userAnswer.setCorrect(isCorrect);

        userAnswerDao.saveUserAnswer(userAnswer);

        AnswerModel answerModel = new ModelConverter().toAnswerModel(userAnswer);
        return SaveUserAnswerResult.builder().withAnswer(answerModel).build();
    }
}

