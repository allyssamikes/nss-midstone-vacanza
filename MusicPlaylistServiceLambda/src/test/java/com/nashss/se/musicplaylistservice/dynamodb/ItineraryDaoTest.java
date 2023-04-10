package com.nashss.se.musicplaylistservice.dynamodb;

import com.nashss.se.musicplaylistservice.dynamodb.models.Itinerary;
import com.nashss.se.musicplaylistservice.exceptions.PlaylistNotFoundException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;

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


    private PlaylistDao playlistDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        playlistDao = new PlaylistDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getPlaylist_withPlaylistId_callsMapperWithPartitionKey() {
        // GIVEN
        String playlistId = "playlistId";
        when(dynamoDBMapper.load(Itinerary.class, playlistId)).thenReturn(new Itinerary());

        // WHEN
        Itinerary playlist = playlistDao.getPlaylist(playlistId);

        // THEN
        assertNotNull(playlist);
        verify(dynamoDBMapper).load(Itinerary.class, playlistId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYLIST_PLAYLISTNOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getPlaylist_playlistIdNotFound_throwsPlaylistNotFoundException() {
        // GIVEN
        String nonexistentPlaylistId = "NotReal";
        when(dynamoDBMapper.load(Itinerary.class, nonexistentPlaylistId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(PlaylistNotFoundException.class, () -> playlistDao.getPlaylist(nonexistentPlaylistId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYLIST_PLAYLISTNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void savePlaylist_callsMapperWithPlaylist() {
        // GIVEN
        Itinerary playlist = new Itinerary();

        // WHEN
        Itinerary result = playlistDao.savePlaylist(playlist);

        // THEN
        verify(dynamoDBMapper).save(playlist);
        assertEquals(playlist, result);
    }
}
