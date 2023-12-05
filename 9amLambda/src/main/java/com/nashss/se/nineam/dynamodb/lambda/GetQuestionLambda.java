package com.nashss.se.nineam.dynamodb.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.nineam.activity.requests.GetQuestionRequest;
import com.nashss.se.nineam.activity.results.GetQuestionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetQuestionLambda
    extends LambdaActivityRunner<GetQuestionRequest, GetQuestionResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetQuestionRequest>, LambdaResponse> {

        private final Logger log = LogManager.getLogger();

        @Override
        public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetQuestionRequest> input, Context context) {
            log.info("handleRequest");
            return super.runActivity(
                    () -> {
                        GetQuestionRequest unauthenticatedRequest = input.fromPath(path -> GetQuestionRequest.builder()
                                .withDate(path.get("date"))
                                .build());
                        return unauthenticatedRequest;
                    },
                    (request, serviceComponent) ->
                            serviceComponent.provideGetQuestionActivity().handleRequest(request)
            );
        }

    }


