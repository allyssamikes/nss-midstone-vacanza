package com.nashss.se.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.CreateActivityActivity;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateActivityRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateActivityResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY.MUSEUM;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateActivityActivityTest {
    @Mock
    private ActivityDao activityDao;

    private CreateActivityActivity createActivityActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createActivityActivity = new CreateActivityActivity(activityDao);
    }

    @Test
    public void handleRequest_withTags_createsAndSavesActivityWithTags() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedCityCountry = "expectedCityCountry";
        String expectedAddress = "expectedAddress";
        TYPE_OF_ACTIVITY expectedType = MUSEUM;
        Boolean expectedKidFriendly = true;
        Boolean expectedWeatherPermitting = false;


        CreateActivityRequest request = CreateActivityRequest.builder()
                .withName(expectedName)
                .withCityCountry(expectedCityCountry)
                .withAddress(expectedAddress)
                .withType(expectedType)
                .withKidFriendly(expectedKidFriendly)
                .withWeatherPermitting(expectedWeatherPermitting)
                .build();

        // WHEN
        CreateActivityResult result = createActivityActivity.handleRequest(request);

        // THEN
        verify(activityDao).saveActivity(any(Activity.class));

        assertEquals(expectedName, result.getActivity().getName());
        assertEquals(expectedCityCountry, result.getActivity().getCityCountry());
        assertEquals(expectedAddress, result.getActivity().getAddress());
        assertEquals(expectedType, result.getActivity().getTYPE_OF_ACTIVITY());
        assertEquals(expectedKidFriendly, result.getActivity().getKidFriendly());
        assertEquals(expectedWeatherPermitting, result.getActivity().getWeatherPermitting());
    }

    @Test
    public void handleRequest_noTags_createsAndSavesActivityWithoutTags() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedCityCountry = "expectedCityCountry";

        CreateActivityRequest request = CreateActivityRequest.builder()
                .withName(expectedName)
                .withCityCountry(expectedCityCountry)
                .build();

        // WHEN
        CreateActivityResult result = createActivityActivity.handleRequest(request);

        // THEN
        verify(activityDao).saveActivity(any(Activity.class));

        assertEquals(expectedName, result.getActivity().getName());
        assertEquals(expectedCityCountry, result.getActivity().getCityCountry());
        assertNull(result.getActivity().getAddress());
        assertNull(result.getActivity().getTYPE_OF_ACTIVITY());
        assertNull(result.getActivity().getKidFriendly());
        assertNull(result.getActivity().getWeatherPermitting());
    }

    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        CreateActivityRequest request = CreateActivityRequest.builder()
                .withName("I'm illegal")
                .withCityCountry("cityCountry")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createActivityActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_invalidCityCountry_throwsInvalidAttributeValueException() {
        // GIVEN
        CreateActivityRequest request = CreateActivityRequest.builder()
                .withName("AllOK")
                .withCityCountry("Jemma's \"illegal\" cityCountry")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createActivityActivity.handleRequest(request));
    }
}