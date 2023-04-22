package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.RemoveActivityFromItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.RemoveActivityFromItineraryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveActivityFromItineraryLambda
        extends LambdaActivityRunner<RemoveActivityFromItineraryRequest, RemoveActivityFromItineraryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveActivityFromItineraryRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveActivityFromItineraryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveActivityFromItineraryRequest unauthenticatedRequest = input.fromBody(RemoveActivityFromItineraryRequest.class);
                    return input.fromUserClaims(claims ->
                            RemoveActivityFromItineraryRequest.builder()
                                    .withTripName(unauthenticatedRequest.getTripName())
                                    .withEmail(unauthenticatedRequest.getEmail())
                                    .withCityCountry(unauthenticatedRequest.getCityCountry())
                                    .withName(unauthenticatedRequest.getName())
                                    .build());

                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveActivityFromItineraryActivity().handleRequest(request)

        );
    }
}

