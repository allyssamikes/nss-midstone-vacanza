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

public class AddActivityToItineraryActivity {
    private final ItineraryDao itineraryDao;
    private final ActivityDao activityDao;
    @Inject
    public AddActivityToItineraryActivity(ItineraryDao itineraryDao, ActivityDao activityDao) {
        this.itineraryDao = itineraryDao;
        this.activityDao = activityDao;
    }

    public AddActivityToItineraryResult handleRequest(
            final AddActivityToItineraryRequest addActivityToItineraryRequest) {


        String tripName = addActivityToItineraryRequest.getTripName();
        String email = addActivityToItineraryRequest.getEmail();

        Itinerary itinerary;
        try {itinerary = itineraryDao.getItinerary(email, tripName);
        } catch (ItineraryNotFoundException ex) {
            throw new ItineraryNotFoundException("Itinerary is not in our database.");
        }

        String cityCountry = addActivityToItineraryRequest.getCityCountry();
        String name = addActivityToItineraryRequest.getName();
        Activity activityToAdd;

        try {
            activityToAdd = activityDao.getActivity(cityCountry, name);
        } catch (ActivityNotFoundException ex) {
            throw new ActivityNotFoundException("Activity is not in our database.");
        }
        List<Activity> activityList = new ArrayList<>(  itinerary.getActivities());
        if(activityList == null || activityList.size() ==0){
            activityList = new ArrayList<>();
        }

        activityList.add(activityToAdd);
        itinerary.setActivities(activityList);
        itineraryDao.saveItinerary(itinerary);

        List<ActivityModel> activityModels = new ArrayList<>();
        for(Activity activity: activityList) {
            ActivityModel model = new VModelConverter().toActivityModel(activity);
            activityModels.add(model);
        }

        //List<ActivityModel> activityModels = new VModelConverter().toActivityModelList(itinerary.getActivities());
        return AddActivityToItineraryResult.builder()
                .withActivitiesList(activityModels)
                .build();
    }
}
