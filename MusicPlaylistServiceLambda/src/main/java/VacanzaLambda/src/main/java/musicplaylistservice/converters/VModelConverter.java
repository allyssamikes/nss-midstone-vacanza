package VacanzaLambda.src.main.java.musicplaylistservice.converters;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class VModelConverter {

    /**
     * Converts an itinerary to an ItineraryModels.
     *
     * @param itinerary the Itinerary to convert to ItineraryModel.
     * @return the converted ItineraryModel
     */
    public ItineraryModel toItineraryModel(Itinerary itinerary) {

        return ItineraryModel.builder()
                .withEmail(itinerary.getEmail())
                .withTripName(itinerary.getTripName())
                .withActivities(itinerary.getActivities())
                .withCities(itinerary.getCities())
                .withUsers(itinerary.getUsers())
                .withTags(itinerary.getTags())
                .build();
    }
    /**
     * Converts an activity to an ActivityModel.
     *
     * @param activity the activity  to convert to ActivityModel.
     * @return The converted ActivityModel
     */
    public ActivityModel toActivityModel(Activity activity) {
        return ActivityModel.builder()
                .withName(activity.getName())
                .withCityCountry(activity.getCityCountry())
                .withAddress(activity.getAddress())
                .withTypeOfActivity(activity.getTYPE_OF_ACTIVITY())
                .withKidFriendly(activity.getKidFriendly())
                .withWeatherPermitting(activity.getWeatherPermitting())
                .build();
    }

    /**
     * Converts a list of Itineraries to a list of ItineraryModels.
     *
     * @param itineraries The Itinerary to convert to ItineraryModels
     * @return The converted list of ItineraryModels
     */
    public List<ItineraryModel> toItineraryModelList(List<Itinerary> itineraries) {
        List<ItineraryModel> itineraryModels = new ArrayList<>();

        for (Itinerary i : itineraries) {
            itineraryModels.add(toItineraryModel(i));
        }

        return itineraryModels;
    }

    /**
     * Converts a list of activities to a list of ActivityModels.
     *
     * @param activities the activity  to convert to ActivityModels
     * @return The converted list of ActivityModels
     */public List<ActivityModel> toActivityModelList(List<Activity> activities) {
        List<ActivityModel> activitiesModels = new ArrayList<>();

        for (Activity a : activities) {
            activitiesModels.add(toActivityModel(a));
        }

        return activitiesModels;
    }
}
