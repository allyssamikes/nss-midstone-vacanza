package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.AddActivityToItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.AddActivityToItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import static org.junit.jupiter.api.Assertions.*;

class AddActivityToItineraryActivityTest {
    @Mock
    private ItineraryDao itineraryDao;
    @Mock
    private ActivityDao activityDao;
    @InjectMocks
    private AddActivityToItineraryActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        activity = new AddActivityToItineraryActivity(itineraryDao, activityDao);
    }

    @Test
    void handleRequest() {
        String expectedName = "expectedName";
        List<String> expectedTags = new ArrayList<>();
        String expectedTag = "test";
        expectedTags.add(expectedTag);
        List<String> expectedCities = new ArrayList<>();
        String expectedCity = "Nashville";
        expectedCities.add(expectedCity);
        List<String> expectedUsers = new ArrayList<>();
        String expectedUser = "fido";
        expectedUsers.add(expectedUser);

        String expectedEmail = "jskfl@kjflds";
        AddActivityToItineraryRequest request1 = AddActivityToItineraryRequest.builder()
                .withTripName(expectedName)
                .withEmail(expectedEmail)
                .withName("MOMA")
                .withCityCountry(expectedCity)
                .build();

        //WHen
       // AddActivityToItineraryResult result1 = activity.handleRequest(request1);
       // verify(itineraryDao).saveItinerary(any(Itinerary.class));
        verify(itineraryDao).getItinerary(expectedEmail, expectedName);
        //verify(activityDao).getActivity(expectedCity, "MOMA");
        assertEquals("MOMA", request1.getName());
        assertEquals(expectedCity, request1.getCityCountry());
        assertEquals(expectedEmail, request1.getEmail());
        assertEquals(expectedName, request1.getTripName());

    }
}