package com.nashss.se.nineam.activity;
import com.nashss.se.nineam.activity.requests.ViewHistoryRequest;
import com.nashss.se.nineam.activity.results.ViewHistoryResult;
import com.nashss.se.nineam.converters.ModelConverter;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;
import com.nashss.se.nineam.dynamodb.models.UserAnswer;
import com.nashss.se.nineam.models.AnswerModel;
import javax.inject.Inject;
import java.util.List;

public class ViewHistoryActivity {
    private final UserAnswerDao userAnswerDao;
    @Inject
    public ViewHistoryActivity(UserAnswerDao userAnswerDao) {
        this.userAnswerDao = userAnswerDao;
    }
    public ViewHistoryResult handleRequest(final ViewHistoryRequest getHistoryRequest) {
        List<UserAnswer> userAnswers = userAnswerDao.getAllUserAnswers(getHistoryRequest.getUserId());
        List<AnswerModel> userAnswerModels = new ModelConverter().toAnswerModelList(userAnswers);
        return ViewHistoryResult.builder()
                .withAnswerModelList(userAnswerModels)
                .build();
    }
}

