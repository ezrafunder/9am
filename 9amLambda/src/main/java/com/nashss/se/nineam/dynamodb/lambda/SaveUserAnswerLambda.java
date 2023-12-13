package com.nashss.se.nineam.dynamodb.lambda;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.nineam.activity.requests.SaveUserAnswerRequest;
import com.nashss.se.nineam.activity.results.SaveUserAnswerResult;

public class SaveUserAnswerLambda
        extends LambdaActivityRunner<SaveUserAnswerRequest, SaveUserAnswerResult>
        implements RequestHandler<AuthenticatedLambdaRequest<SaveUserAnswerRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<SaveUserAnswerRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    SaveUserAnswerRequest unauthenticatedRequest = input.fromBody(SaveUserAnswerRequest.class);
                    return input.fromUserClaims(claims ->
                            SaveUserAnswerRequest.builder()
                                    .withDate(unauthenticatedRequest.getDate())
                                    .withUserChoice(unauthenticatedRequest.getUserChoice())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideSaveUserAnswerActivity().handleRequest(request)

        );

    }
}

