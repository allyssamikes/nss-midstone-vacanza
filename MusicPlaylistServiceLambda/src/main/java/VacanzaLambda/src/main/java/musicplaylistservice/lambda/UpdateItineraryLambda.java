package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.UpdateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.UpdateItineraryResult;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class UpdateItineraryLambda
        extends LambdaActivityRunner<UpdateItineraryRequest, UpdateItineraryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateItineraryRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateItineraryRequest> input, Context context) {
        return null;
    }
}

