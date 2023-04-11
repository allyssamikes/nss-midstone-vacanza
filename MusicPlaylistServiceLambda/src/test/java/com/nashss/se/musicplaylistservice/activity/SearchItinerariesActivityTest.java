package com.nashss.se.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.SearchItinerariesActivity;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchItinerariesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchItinerariesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SearchItinerariesActivityTest {

    @Mock
    private ItineraryDao itineraryDao;

    private SearchItinerariesActivity searchItinerariesActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        searchItinerariesActivity = new SearchItinerariesActivity(itineraryDao);
    }

    @Test
    public void handleRequest_whenItinerariesMatchCitiesSearch_returnsItineraryModelListInResult() {
        // GIVEN
        String criteria = "barcelona";
        String[] criteriaArray = {criteria};
        List<String> cities = new ArrayList<>();
        cities.add("barcelona");
        Itinerary one = new Itinerary();
        one.setEmail("email1");
        one.setTripName("spain 2022");
        one.setCities(cities);
        Itinerary two = new Itinerary();
        two.setEmail("email2");
        two.setTripName("spain 2024");
        one.setCities(cities);

        // need to discuss tags?

        List<Itinerary> expected = List.of(one, two);

        when(itineraryDao.searchItinerary(criteriaArray)).thenReturn(expected);

        SearchItinerariesRequest request = SearchItinerariesRequest.builder()
                .withCriteria(criteria)
                .build();

        // WHEN
        SearchItinerariesResult result = searchItinerariesActivity.handleRequest(request);

        // THEN
        List<ItineraryModel> resultItineraries = result.getItineraries();
        assertEquals(expected.size(), resultItineraries.size());

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i).getEmail(), resultItineraries.get(i).getEmail());
            assertEquals(expected.get(i).getTripName(), resultItineraries.get(i).getTripName());
        }
    }

    @Test
    public void handleRequest_whenItinerariesMatchTripNameSearch_returnsItineraryModelListInResult() {
        // GIVEN
        String criteria = "spain";
        String[] criteriaArray = {criteria};
        Itinerary one = new Itinerary();
        one.setEmail("email1");
        one.setTripName("spain 2022");
        Itinerary two = new Itinerary();
        two.setEmail("email2");
        two.setTripName("spain 2024");

        // need to discuss tags?

        List<Itinerary> expected = List.of(one, two);

        when(itineraryDao.searchItinerary(criteriaArray)).thenReturn(expected);

        SearchItinerariesRequest request = SearchItinerariesRequest.builder()
                .withCriteria(criteria)
                .build();

        // WHEN
        SearchItinerariesResult result = searchItinerariesActivity.handleRequest(request);

        // THEN
        List<ItineraryModel> resultItineraries = result.getItineraries();
        assertEquals(expected.size(), resultItineraries.size());

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i).getEmail(), resultItineraries.get(i).getEmail());
            assertEquals(expected.get(i).getTripName(), resultItineraries.get(i).getTripName());
        }
    }

    @Test
    public void handleRequest_withNullCriteria_isIdenticalToEmptyCriteria() {
        // GIVEN
        String criteria = null;
        ArgumentCaptor<String[]> criteriaArray = ArgumentCaptor.forClass(String[].class);

        when(itineraryDao.searchItinerary(criteriaArray.capture())).thenReturn(List.of());

        SearchItinerariesRequest request = SearchItinerariesRequest.builder()
                .withCriteria(criteria)
                .build();

        // WHEN
        SearchItinerariesResult result = searchItinerariesActivity.handleRequest(request);

        // THEN
        assertEquals(0, criteriaArray.getValue().length, "Criteria Array should be empty");
    }

}
