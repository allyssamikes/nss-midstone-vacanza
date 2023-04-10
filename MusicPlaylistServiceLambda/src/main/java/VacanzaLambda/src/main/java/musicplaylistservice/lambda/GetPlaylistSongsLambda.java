package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetPlaylistSongsRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPlaylistSongsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetPlaylistSongsLambda
        extends LambdaActivityRunner<GetPlaylistSongsRequest, GetPlaylistSongsResult>
        implements RequestHandler<com.nashss.se.musicplaylistservice.lambda.LambdaRequest<GetPlaylistSongsRequest>, com.nashss.se.musicplaylistservice.lambda.LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlaylistSongsRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPathAndQuery((path, query) ->
                    GetPlaylistSongsRequest.builder()
                            .withId(path.get("id"))
                            .withOrder(query.get("order"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetPlaylistSongsActivity().handleRequest(request)
        );
    }
}

