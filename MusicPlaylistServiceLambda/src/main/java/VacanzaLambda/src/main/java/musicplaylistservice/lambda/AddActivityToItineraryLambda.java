package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.AddActivityToItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.AddActivityToItineraryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import VacanzaLambda.src.main.java.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.lambda.LambdaActivityRunner;
import VacanzaLambda.src.main.java.musicplaylistservice.lambda.LambdaResponse;

public class AddActivityToItineraryLambda
        extends LambdaActivityRunner<AddActivityToItineraryRequest, AddActivityToItineraryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddActivityToItineraryRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
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

