package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.CreatePlaylistRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePlaylistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreatePlaylistLambda
        extends LambdaActivityRunner<CreatePlaylistRequest, CreatePlaylistResult>
        implements RequestHandler<com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest<CreatePlaylistRequest>, com.nashss.se.musicplaylistservice.lambda.LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePlaylistRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreatePlaylistRequest unauthenticatedRequest = input.fromBody(CreatePlaylistRequest.class);
                return input.fromUserClaims(claims ->
                        CreatePlaylistRequest.builder()
                                .withName(unauthenticatedRequest.getName())
                                .withTags(unauthenticatedRequest.getTags())
                                .withCustomerId(claims.get("email"))
                                .withCustomerName(claims.get("name"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreatePlaylistActivity().handleRequest(request)
        );
    }
}

