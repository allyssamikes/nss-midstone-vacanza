package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.SearchPlaylistsRequest;
import com.nashss.se.musicplaylistservice.activity.results.SearchPlaylistsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchPlaylistsLambda
        extends LambdaActivityRunner<SearchPlaylistsRequest, SearchPlaylistsResult>
        implements RequestHandler<com.nashss.se.musicplaylistservice.lambda.LambdaRequest<SearchPlaylistsRequest>, com.nashss.se.musicplaylistservice.lambda.LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchPlaylistsRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromQuery(query ->
                    SearchPlaylistsRequest.builder()
                            .withCriteria(query.get("q"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideSearchPlaylistsActivity().handleRequest(request)
        );
    }
}

