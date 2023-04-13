package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryActivitiesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetItineraryActivitiesLambda
        extends LambdaActivityRunner<GetItineraryActivitiesRequest, GetItineraryActivitiesResult>
        implements RequestHandler<LambdaRequest<GetItineraryActivitiesRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetItineraryActivitiesRequest> input, Context context) {
        return null;
    }
}

