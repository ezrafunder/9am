package com.nashss.se.nineam.dynamodb;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.nineam.dynamodb.models.Question;
import com.nashss.se.nineam.exceptions.QuestionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionDAOTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private QuestionDAO questionDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestion() {
        String date = "11-19-2023";
        Question mockQuestion = new Question();
        mockQuestion.setDate(date);


        when(dynamoDBMapper.load(eq(Question.class), eq(date))).thenReturn(mockQuestion);

        Question resultQuestion = questionDAO.getQuestion(date);

        assertNotNull(resultQuestion);
        assertEquals(date, resultQuestion.getDate());

        verify(dynamoDBMapper, times(1)).load(Question.class, date);
    }

    @Test
    void testGetQuestionNotFound() {
        String date = "11-19-2023";

        when(dynamoDBMapper.load(eq(Question.class), eq(date))).thenReturn(null);

        assertThrows(QuestionNotFoundException.class, () -> questionDAO.getQuestion(date));

        verify(dynamoDBMapper, times(1)).load(Question.class, date);
    }
}
