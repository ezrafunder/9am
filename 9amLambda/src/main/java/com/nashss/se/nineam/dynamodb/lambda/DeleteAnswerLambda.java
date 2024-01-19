package com.nashss.se.nineam.dynamodb.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.nineam.activity.requests.DeleteAnswerRequest;
import com.nashss.se.nineam.activity.requests.SaveUserAnswerRequest;
import com.nashss.se.nineam.activity.results.DeleteAnswerResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAnswerLambda
        extends LambdaActivityRunner<DeleteAnswerRequest, DeleteAnswerResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteAnswerRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteAnswerRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    DeleteAnswerRequest unauthenticatedRequest = input.fromPath(path -> DeleteAnswerRequest.builder()
                            .withQuestionId(path.get("questionId"))
                            .build());
                    return input.fromUserClaims(claims ->
                            DeleteAnswerRequest.builder()
                                    .withQuestionId(unauthenticatedRequest.getQuestionId())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteAnswerActivity().handleRequest(request)
        );
    }
}

