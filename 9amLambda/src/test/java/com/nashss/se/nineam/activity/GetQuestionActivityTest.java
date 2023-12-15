package com.nashss.se.nineam.activity;
import com.nashss.se.nineam.activity.requests.GetQuestionRequest;
import com.nashss.se.nineam.activity.results.GetQuestionResult;
import com.nashss.se.nineam.dynamodb.QuestionDAO;
import com.nashss.se.nineam.dynamodb.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class GetQuestionActivityTest {
    @Mock
    private QuestionDAO questionDAO;
    @InjectMocks
    private GetQuestionActivity getQuestionActivity;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void handleRequest_validRequest_returnsQuestionResult() {
        String testDate = "2023-01-01";
        Question testQuestion = new Question();
        testQuestion.setDate(testDate);

        when(questionDAO.getQuestion(testDate)).thenReturn(testQuestion);
        GetQuestionRequest request = new GetQuestionRequest(testDate);
        GetQuestionResult result = getQuestionActivity.handleRequest(request);

        assertEquals(testDate, result.getQuestion().getDate());
    }
}
