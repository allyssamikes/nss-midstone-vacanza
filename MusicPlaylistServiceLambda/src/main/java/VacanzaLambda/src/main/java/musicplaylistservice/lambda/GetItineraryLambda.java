package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetItineraryLambda
        extends LambdaActivityRunner<GetItineraryRequest, GetItineraryResult>
        implements RequestHandler<LambdaRequest<GetItineraryRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetItineraryRequest> input, Context context) {
        return null;
    }
}

