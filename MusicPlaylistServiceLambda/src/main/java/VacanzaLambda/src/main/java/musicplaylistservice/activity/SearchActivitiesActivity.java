package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ActivityNotFoundException;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetItineraryActivity for VACANZA'sSearchActivitiesActivity  API.
 *
 * This API allows the customer to search for activities by city and country.
 */
public class SearchActivitiesActivity {
    private final Logger log = LogManager.getLogger();
    private final ActivityDao activityDao;

    /**
     * Instantiates a new SearchActivitiesActivity object.
     *
     *
     * @param activityDao ActivityDao to access the activities table.
     */
    @Inject
    public SearchActivitiesActivity( ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    /**
     * This method handles the incoming request by searching for  itinerary from the database.
     * <p>
     * It then returns the matching activities, or if none are found, ActivitiesNotFoundException.
     *
     * @param searchActivitiesRequest request object containing the search criteria
     * @return searchActivitiesResult result object containing the activities that match the
     * search criteria.
     */
    public SearchActivitiesResult handleRequest(final SearchActivitiesRequest searchActivitiesRequest) {
        log.info("Received SearchActivitiesRequest {}{}", searchActivitiesRequest, searchActivitiesRequest.getCityCountry());

        List<Activity> activityList;
        try {
            activityList = activityDao.getActivitiesByCityCountry(searchActivitiesRequest.getCityCountry());
        } catch (ActivityNotFoundException ex) {
            throw new ActivityNotFoundException("No activities found for this city.");
        }
        List<ActivityModel> activityModelList = new ArrayList<>();
        for(Activity activity: activityList) {
            activityModelList.add(new VModelConverter().toActivityModel(activity));
        }
        return SearchActivitiesResult.builder()
                .withActivityModels(activityModelList)
                .build();

    }
}
