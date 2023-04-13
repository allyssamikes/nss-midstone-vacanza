package com.nashss.se.musicplaylistservice.dynamodb;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ItineraryNotFoundException;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsConstants;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItineraryDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;


    private ItineraryDao itineraryDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        itineraryDao = new ItineraryDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getItinerary_withItineraryEmail_callsMapperWithPartitionKey() {
        // GIVEN
        String email = "email";
        String tripName = "tripName";
        when(dynamoDBMapper.load(Itinerary.class, email, tripName)).thenReturn(new Itinerary());

        // WHEN
        Itinerary itinerary= itineraryDao.getItinerary(email, tripName);

        // THEN
        assertNotNull(itinerary);
        verify(dynamoDBMapper).load(Itinerary.class, email, tripName);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETINTERARY_ITINERARYNOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getItinerary_ItineraryNotFound_throwsItineraryNotFoundException() {
        // GIVEN
        String nonexistentItinerary = "NotReal";
        when(dynamoDBMapper.load(Itinerary.class, nonexistentItinerary)).thenReturn(null);

        // WHEN + THEN
        assertThrows(ItineraryNotFoundException.class, () ->itineraryDao.getItinerary(nonexistentItinerary, nonexistentItinerary));

        verify(metricsPublisher).addCount(eq(MetricsConstants.GETINTERARY_ITINERARYNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveItinerary_callsMapperWithItinerary() {
        // GIVEN
        Itinerary itinerary = new Itinerary();

        // WHEN
        Itinerary result = itineraryDao.saveItinerary(itinerary);

        // THEN
        verify(dynamoDBMapper).save(itinerary);
        assertEquals(itinerary, result);
    }
}