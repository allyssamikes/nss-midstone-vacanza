package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryActivitiesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryActivitiesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetItineraryActivitiesActivityTest {
    @Mock
    private ItineraryDao itineraryDao;

    private GetItineraryActivitiesActivity getItineraryActivitiesActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getItineraryActivitiesActivity = new GetItineraryActivitiesActivity(itineraryDao);
    }
    @Test
    public void handleRequest_itineraryExists_returnsActivities() {
        String expectedEmail = "email@email.com";
        String expectedTripName = "expectedTripName";

        Itinerary itinerary = new Itinerary();
        itinerary.setEmail(expectedEmail);
        itinerary.setTripName(expectedTripName);
        when(itineraryDao.getItinerary(expectedEmail, expectedTripName)).thenReturn(itinerary);

        String expectedName = "expectedName";
        String expectedCityCountry = "expectedCityCountry";

        List<Activity> activities = new ArrayList<>();
        Activity activity = new Activity();
        activity.setName(expectedName);
        activity.setCityCountry(expectedCityCountry);
        activities.add(activity);
        itinerary.setActivities(activities);

        GetItineraryActivitiesRequest request = GetItineraryActivitiesRequest.builder()
                .withEmail(expectedEmail)
                .withTripName(expectedTripName)
                .build();

        GetItineraryActivitiesResult result = getItineraryActivitiesActivity.handleRequest(request);

        assertEquals(result.getActivityList().get(0).getName(), activities.get(0).getName());
    }
}
