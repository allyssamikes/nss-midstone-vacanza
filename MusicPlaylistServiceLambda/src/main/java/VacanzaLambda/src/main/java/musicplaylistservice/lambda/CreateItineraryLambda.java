package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateItineraryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateItineraryLambda
        extends LambdaActivityRunner<CreateItineraryRequest, CreateItineraryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateItineraryRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateItineraryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateItineraryRequest unauthenticatedRequest = input.fromBody(CreateItineraryRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateItineraryRequest.builder()
                                    .withTripName(unauthenticatedRequest.getTripName())
                                    .withTags(unauthenticatedRequest.getTags())
                                    .withEmail(claims.get("email"))
                                    .withUsers(unauthenticatedRequest.getUsers())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateItineraryActivity().handleRequest(request)
        );
    }
}
