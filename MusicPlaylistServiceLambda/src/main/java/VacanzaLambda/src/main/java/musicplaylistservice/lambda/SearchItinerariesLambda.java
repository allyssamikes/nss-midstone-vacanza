package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchItinerariesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchItinerariesResult;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;



public class SearchItinerariesLambda
        extends LambdaActivityRunner<SearchItinerariesRequest, SearchItinerariesResult>
        implements RequestHandler<LambdaRequest<SearchItinerariesRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchItinerariesRequest> input, Context context) {
        return null;
    }
}

