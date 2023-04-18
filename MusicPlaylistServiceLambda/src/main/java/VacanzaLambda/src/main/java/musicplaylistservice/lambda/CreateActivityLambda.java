package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateActivityRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateActivityResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateActivityLambda
        extends LambdaActivityRunner<CreateActivityRequest, CreateActivityResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateActivityRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateActivityRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateActivityRequest unauthenticatedRequest = input.fromBody(CreateActivityRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateActivityRequest.builder()
                                    .withName(unauthenticatedRequest.getName())
                                    .withCityCountry(unauthenticatedRequest.getCityCountry())
                                    .withAddress(unauthenticatedRequest.getAddress())
                                    .withType(unauthenticatedRequest.getType())
                                    .withKidFriendly(unauthenticatedRequest.getKidFriendly())
                                    .withWeatherPermitting(unauthenticatedRequest.getWeatherPermitting())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateActivityActivity().handleRequest(request)
        );
    }
}
