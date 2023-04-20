package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryActivitiesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetItineraryActivitiesLambda
        extends LambdaActivityRunner<GetItineraryActivitiesRequest, GetItineraryActivitiesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetItineraryActivitiesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetItineraryActivitiesRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetItineraryActivitiesRequest.builder()
                                .withEmail(path.get("email"))
                                .withTripName(path.get("tripName"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetItineraryActivitiesActivity().handleRequest(request)
        );
    }
}
