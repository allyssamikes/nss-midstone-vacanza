package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.RemoveActivityFromItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.RemoveActivityFromItineraryResult;
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
public class RemoveActivityFromItineraryActivity {

    private final ItineraryDao itineraryDao;
    private final ActivityDao activityDao;
   @Inject
   public RemoveActivityFromItineraryActivity(ItineraryDao itineraryDao, ActivityDao activityDao) {
       this.itineraryDao = itineraryDao;
       this.activityDao = activityDao;
   }

    public RemoveActivityFromItineraryResult handleRequest(
            final RemoveActivityFromItineraryRequest activityToItineraryRequest) {

        String tripName = activityToItineraryRequest.getTripName();
        String email = activityToItineraryRequest.getEmail();

        Itinerary itinerary;
        try {itinerary = itineraryDao.getItinerary(email, tripName);
        } catch (ItineraryNotFoundException ex) {
            throw new ItineraryNotFoundException("Itinerary is not in our database.");
        }

        Activity activityToRemove;

        try {
            activityToRemove = activityDao.getActivity(activityToItineraryRequest.getCityCountry(),
                    activityToItineraryRequest.getName());
        } catch (ActivityNotFoundException ex) {
            throw new ActivityNotFoundException("Activity is not in our database.");
        }
        List<Activity> activityList;
        List<Activity> newActivityList = new ArrayList<>();

        if (itinerary.getActivities() == null) {
            throw new ActivityNotFoundException("This itinerary has no activities.");
        } else  {
            activityList = new ArrayList<>(itinerary.getActivities());

        }
        for (Activity activity:activityList) {
            if(!activityToRemove.equals(activity)) {
                newActivityList.add(activity);
            }
        }

        itinerary.setActivities(newActivityList);
        itineraryDao.saveItinerary(itinerary);

        List<ActivityModel> activityModels = new ArrayList<>();
        for(Activity activity: newActivityList) {
            ActivityModel model = new VModelConverter().toActivityModel(activity);
            activityModels.add(model);
        }

        return RemoveActivityFromItineraryResult.builder()
                .withActivitiesList(activityModels)
                .build();
    }
}
