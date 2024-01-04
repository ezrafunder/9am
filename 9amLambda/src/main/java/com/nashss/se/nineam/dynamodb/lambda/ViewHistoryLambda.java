package com.nashss.se.nineam.dynamodb.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.nineam.activity.requests.ViewHistoryRequest;
import com.nashss.se.nineam.activity.results.ViewHistoryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewHistoryLambda extends LambdaActivityRunner<ViewHistoryRequest, ViewHistoryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<ViewHistoryRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<ViewHistoryRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromUserClaims(claims ->
                        ViewHistoryRequest.builder().withUserId(claims.get("email")).build()),
                (request, serviceComponent) ->
                        serviceComponent.provideViewHistoryActivity().handleRequest(request)
        );
    }
}
