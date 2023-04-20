package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetItineraryActivitiesActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    @Inject
    public GetItineraryActivitiesActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }

    public GetItineraryActivitiesResult handleRequest(final GetItineraryActivitiesRequest getItineraryActivitiesRequest){
        log.info("Received GetItineraryActivitiesRequest {}", getItineraryActivitiesRequest);
        String email = getItineraryActivitiesRequest.getEmail();
        String tripName= getItineraryActivitiesRequest.getTripName();
        Itinerary itinerary = itineraryDao.getItinerary(email, tripName);
        List<Activity> activityList = itinerary.getActivities();

        List<ActivityModel> activityModels = new ArrayList<>();
        for(Activity activity: activityList) {
            ActivityModel model = new VModelConverter().toActivityModel(activity);
            activityModels.add(model);
        }
        return GetItineraryActivitiesResult.builder()
                .withActivityList(activityModels)
                .build();
    }

}