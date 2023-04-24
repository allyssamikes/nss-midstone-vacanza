package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.AddActivityToItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.AddActivityToItineraryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddActivityToItineraryLambda
        extends LambdaActivityRunner<AddActivityToItineraryRequest, AddActivityToItineraryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddActivityToItineraryRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddActivityToItineraryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddActivityToItineraryRequest unauthenticatedRequest = input.fromBody(AddActivityToItineraryRequest.class);
                    return input.fromUserClaims(claims ->
                            AddActivityToItineraryRequest.builder()
                                    .withTripName(unauthenticatedRequest.getTripName())
                                    .withEmail(unauthenticatedRequest.getEmail())
                                    .withCityCountry(unauthenticatedRequest.getCityCountry())
                                    .withName(unauthenticatedRequest.getName())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddActivityToItineraryActivity().handleRequest(request)
        );
    }
}

