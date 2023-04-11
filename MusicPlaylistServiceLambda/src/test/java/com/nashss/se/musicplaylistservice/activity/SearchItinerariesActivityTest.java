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
    public void handleRequest_whenItinerariesMatchSearch_returnsItineraryModelListInResult() {
        // GIVEN
        String criteria = "good";
        String[] criteriaArray = {criteria};
        Itinerary one = new Itinerary();
        one.setEmail("email1");
        one.setTripName("trip1");
        Itinerary two = new Itinerary();
        two.setEmail("email2");
        two.setTripName("trip2");

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
