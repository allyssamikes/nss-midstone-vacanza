package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.AddActivityToItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.AddActivityToItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ActivityNotFoundException;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ItineraryNotFoundException;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AddActivityToItineraryActivity to VACANZA's AddActivityToItinerary API.
 *
 * This API allows the customer to add an activity  to their existing itinerary.
 */
public class AddActivityToItineraryActivity {
    private final ItineraryDao itineraryDao;
    private final ActivityDao activityDao;

    /**
     * Instantiates a new AddActivityToItineraryActivity object.
     *
     * @param itineraryDao ItineraryDao to access the itinerary table.
     * @param activityDao ActivityDao to access the activity table.
     */
    @Inject
    public AddActivityToItineraryActivity(ItineraryDao itineraryDao, ActivityDao activityDao) {
        this.itineraryDao = itineraryDao;
        this.activityDao = activityDao;
    }
    /**
     * This method handles the incoming request by adding an additional activity
     * to an itinerary and persisting the updated itinerary
     * <p>
     * It then returns the updated activity list of the itinerary..
     * <p>
     * If the itinerary does not exist, this should throw a ItineraryNotFoundException.
     * <p>
     * If the activity does not exist, this should throw an ActivityNotFoundException.
     *
     * @param addActivityToItineraryRequest request object containing the email and tripName
     *                                 to retrieve the itinerary data
     * @return AddActivityToItineraryResult result object containing the itinerary's updated list of
     *                                 API defined {@link ActivityModel}s
     */
    public AddActivityToItineraryResult handleRequest(
            final AddActivityToItineraryRequest addActivityToItineraryRequest) {

        String tripName = addActivityToItineraryRequest.getTripName();
        String email = addActivityToItineraryRequest.getEmail();

        Itinerary itinerary;
        try {itinerary = itineraryDao.getItinerary(email, tripName);
        } catch (ItineraryNotFoundException ex) {
            throw new ItineraryNotFoundException("Itinerary is not in our database.");
        }

        Activity activityToAdd;

        try {
            activityToAdd = activityDao.getActivity(addActivityToItineraryRequest.getCityCountry(),
                    addActivityToItineraryRequest.getName());
        } catch (ActivityNotFoundException ex) {
            throw new ActivityNotFoundException("Activity is not in our database.");
        }
        List<Activity> activityList;

        if (itinerary.getActivities() == null) {
            activityList = new ArrayList<>();
        } else  {
            activityList = new ArrayList<>(itinerary.getActivities());
        }

        activityList.add(activityToAdd);
        itinerary.setActivities(activityList);
        itineraryDao.saveItinerary(itinerary);

        List<ActivityModel> activityModels = new ArrayList<>();
        for(Activity activity: activityList) {
            ActivityModel model = new VModelConverter().toActivityModel(activity);
            activityModels.add(model);
        }

        return AddActivityToItineraryResult.builder()
                .withActivitiesList(activityModels)
                .build();
    }
}
