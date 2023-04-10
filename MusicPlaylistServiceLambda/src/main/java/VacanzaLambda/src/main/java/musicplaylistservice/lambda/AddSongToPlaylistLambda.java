package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.AddSongToPlaylistRequest;
import com.nashss.se.musicplaylistservice.activity.results.AddSongToPlaylistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class AddSongToPlaylistLambda
        extends LambdaActivityRunner<AddSongToPlaylistRequest, AddSongToPlaylistResult>
        implements RequestHandler<com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest<AddSongToPlaylistRequest>, com.nashss.se.musicplaylistservice.lambda.LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddSongToPlaylistRequest> input, Context context) {
        return super.runActivity(
            () -> {
                AddSongToPlaylistRequest unauthenticatedRequest = input.fromBody(AddSongToPlaylistRequest.class);
                return input.fromUserClaims(claims ->
                        AddSongToPlaylistRequest.builder()
                                .withId(unauthenticatedRequest.getId())
                                .withAsin(unauthenticatedRequest.getAsin())
                                .withTrackNumber(unauthenticatedRequest.getTrackNumber())
                                .withQueueNext(unauthenticatedRequest.isQueueNext())
                                .withCustomerId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideAddSongToPlaylistActivity().handleRequest(request)
        );
    }
}

