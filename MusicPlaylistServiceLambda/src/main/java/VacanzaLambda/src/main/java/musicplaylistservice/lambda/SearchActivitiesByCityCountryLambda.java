package VacanzaLambda.src.main.java.musicplaylistservice.lambda;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dependency.ServiceComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchActivitiesByCityCountryLambda extends LambdaActivityRunner<SearchActivitiesRequest, SearchActivitiesResult>
        implements RequestHandler<LambdaRequest<SearchActivitiesRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchActivitiesRequest> input, Context context) {
        log.info("SearchActivitiesByCityCountryLambda handle request");
        return super.runActivity(
                () -> input.fromPath(path ->
                        SearchActivitiesRequest.builder()
                                .withCityCountry(path.get("cityCountry"))
                                .build()),
                (SearchActivitiesRequest request, ServiceComponent serviceComponent) ->
                        serviceComponent.provideSearchActivitiesActivity().handleRequest(request)

        );
    }
}
