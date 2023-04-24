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
 * Implements a SearchActivitiesActivity for the SearchActivities API.
 *
 *
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
     * This method handles the incoming request by persisting a new itinerary
     * with the provided name and customer email from the request.
     * <p>
     * It then returns the newly created itinerary.
     * <p>
     * If the provided name has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param searchActivitiesRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return searchActivitiesResult result object containing the API defined {@link ActivityModel}
     */
    public SearchActivitiesResult handleRequest(final SearchActivitiesRequest searchActivitiesRequest) {
        log.info("Received SearchActivitiesRequest {}{}", searchActivitiesRequest, searchActivitiesRequest.getCityCountry());

        List<Activity> activityList;
        try {
            activityList = activityDao.getActivitiesByCityCountry(searchActivitiesRequest.getCityCountry());
        } catch (ActivityNotFoundException ex) {
            throw new ActivityNotFoundException("No activities found for this place.");
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
