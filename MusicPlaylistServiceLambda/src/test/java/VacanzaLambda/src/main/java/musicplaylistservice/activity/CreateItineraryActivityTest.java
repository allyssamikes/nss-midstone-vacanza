package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateActivityRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateActivityResult;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY.MUSEUM;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateItineraryActivityTest {
    @Mock
    private ItineraryDao itineraryDao;
    @InjectMocks
    private CreateItineraryActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    public void handleRequest_withTags_createsAndSavesItineraryWithTags() {
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
        CreateItineraryRequest request1 = CreateItineraryRequest.builder()
                .withTripName(expectedName)
                .withEmail(expectedEmail)
                .withTags(expectedTags)
                .withCities(expectedCities)
                .withUsers(expectedUsers)
                .build();
        //WHen
        CreateItineraryResult result1 = activity.handleRequest(request1);
        //Then
        verify(itineraryDao).saveItinerary(any(Itinerary.class));
        assertEquals(expectedEmail, result1.getItinerary().getEmail());
        assertEquals(expectedName, result1.getItinerary().getTripName());
        assertEquals(expectedCities, result1.getItinerary().getCities());
        assertEquals(expectedTags, result1.getItinerary().getTags());
        assertEquals(expectedUsers, result1.getItinerary().getUsers());

    }

}