
package com.nashss.se.nineam.activity;

import com.nashss.se.nineam.activity.requests.DeleteAnswerRequest;
import com.nashss.se.nineam.activity.results.DeleteAnswerResult;
import com.nashss.se.nineam.dynamodb.UserAnswerDao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteAnswerActivityTest {
    @Test
  public void testHandleRequest_SuccessfulDeletion() {
       UserAnswerDao userAnswerDao = new MockUserAnswerDao(true);

      DeleteAnswerActivity deleteAnswerActivity = new DeleteAnswerActivity(userAnswerDao);

       DeleteAnswerRequest request = new DeleteAnswerRequest("userId123", "questionId456");

       DeleteAnswerResult result = deleteAnswerActivity.handleRequest(request);

       assertTrue(result.isSuccess());
   }


    private static class MockUserAnswerDao extends UserAnswerDao {
        private final boolean deletionResult;

        MockUserAnswerDao(boolean deletionResult) {
            super(null);
            this.deletionResult = deletionResult;
        }

        @Override
        public void deleteAnswer(String userId, String questionId) {
            if (!deletionResult) {
                throw new RuntimeException("Deletion failed");
            }
        }
    }
}

