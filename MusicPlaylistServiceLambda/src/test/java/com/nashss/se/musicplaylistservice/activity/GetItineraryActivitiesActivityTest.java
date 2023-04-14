package com.nashss.se.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.GetItineraryActivitiesActivity;
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
    public void handleRequest_savedItineraryFound_returnsListOfActivities() {
        //GIVEN
        String expectedActivityNameOne = "activity name example";
        String expectedActivityNameTwo = "another activity name example";
        String expectedCityCountry = "Nashville,Usa";

        Activity activityOne = new Activity();
        activityOne.setName(expectedActivityNameOne);
        activityOne.setCityCountry(expectedCityCountry);

        Activity activityTwo = new Activity();
        activityTwo.setName(expectedActivityNameTwo);
        activityTwo.setCityCountry(expectedCityCountry);

        List<Activity> activityList = new ArrayList<>();
        activityList.add(activityOne);
        activityList.add(activityTwo);

        String expectedEmail = "email@email.com";
        String expectedTripName = "expectedTripName";

        Itinerary itinerary = new Itinerary();
        itinerary.setEmail(expectedEmail);
        itinerary.setTripName(expectedTripName);
        itinerary.setActivities(activityList);

        when(itineraryDao.getItinerary(expectedEmail,expectedTripName)).thenReturn(itinerary);

        GetItineraryActivitiesRequest request = GetItineraryActivitiesRequest.builder()
                .withEmail(expectedEmail)
                .withTripName(expectedTripName)
                .build();

        //WHEN
        GetItineraryActivitiesResult result = getItineraryActivitiesActivity.handleRequest(request);

        //THEN
        assertEquals(activityList, result.getActivityList());
    }
}
