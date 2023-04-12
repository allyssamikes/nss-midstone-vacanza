package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchItinerariesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchItinerariesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchItinerariesLambda
        extends LambdaActivityRunner<SearchItinerariesRequest, SearchItinerariesResult>
        implements RequestHandler<LambdaRequest<SearchItinerariesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchItinerariesRequest> input, Context context) {
        log.info("handleRequest");
        System.out.println("Search Itinerries LAMBDA HANDLE REQUEST");
        return super.runActivity(
            () -> input.fromQuery(query ->
                    SearchItinerariesRequest.builder()
                            .withCriteria(query.get("q"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideSearchItinerariesActivity().handleRequest(request)
        );
    }
}

