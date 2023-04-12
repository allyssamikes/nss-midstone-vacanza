package com.nashss.se.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.GetItineraryActivity;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetItineraryActivityTest {
    @Mock
    private ItineraryDao itineraryDao;

    private GetItineraryActivity getItineraryActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getItineraryActivity = new GetItineraryActivity(itineraryDao);
    }

    @Test
    public void handleRequest_savedItineraryFound_returnsItineraryModelInResult() {
        // GIVEN
        String expectedEmail = "email@email.com";
        String expectedTripName = "expectedTripName";

        Itinerary itinerary = new Itinerary();
        itinerary.setEmail(expectedEmail);
        itinerary.setTripName(expectedTripName);


        when(itineraryDao.getItinerary(expectedEmail)).thenReturn(itinerary);

        GetItineraryRequest request = GetItineraryRequest.builder()
                .withEmail(expectedEmail)
                .withTripName(expectedTripName)
                .build();

        // WHEN
        GetItineraryResult result = getItineraryActivity.handleRequest(request);

        // THEN
        assertEquals(expectedEmail, result.getItinerary().getEmail());
        assertEquals(expectedTripName, result.getItinerary().getTripName());
    }
}
