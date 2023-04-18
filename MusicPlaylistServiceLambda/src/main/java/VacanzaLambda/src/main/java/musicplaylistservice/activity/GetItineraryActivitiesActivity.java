package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
public class GetItineraryActivitiesActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    @Inject
    public GetItineraryActivitiesActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }

    public GetItineraryActivitiesResult handleRequest(final GetItineraryActivitiesRequest getItineraryActivitiesRequest){
        log.info("Received GetItineraryActivitiesRequest {}", getItineraryActivitiesRequest);

        Itinerary itinerary = itineraryDao.getItinerary(getItineraryActivitiesRequest.getEmail(),
                getItineraryActivitiesRequest.getTripName());

        List<ActivityModel> activityModels = new VModelConverter().toActivityModelList(itinerary.getActivities());
        return GetItineraryActivitiesResult.builder().withActivityList(activityModels).build();
    }

}