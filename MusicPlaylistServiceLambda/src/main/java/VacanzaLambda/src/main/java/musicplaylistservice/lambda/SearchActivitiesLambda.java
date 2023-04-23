package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dependency.ServiceComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SearchActivitiesLambda extends LambdaActivityRunner<SearchActivitiesRequest, SearchActivitiesResult>
            implements RequestHandler<LambdaRequest<SearchActivitiesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchActivitiesRequest> input, Context context) {
 //       SearchActivitiesRequest unauthenticatedRequest = input.fromBody(SearchActivitiesRequest.class);
        return super.runActivity(
                () -> input.fromQuery(query ->
                        SearchActivitiesRequest.builder()
                                .withCityCountry(query.get("cityCountry")).build()),
                (SearchActivitiesRequest request, ServiceComponent serviceComponent) ->
                        serviceComponent.provideSearchActivitiesActivity().handleRequest(request)
        );

    }
}
