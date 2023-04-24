package VacanzaLambda.src.main.java.musicplaylistservice.dynamodb;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ActivityNotFoundException;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ActivityDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;


    private ActivityDao activityDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        activityDao = new ActivityDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getActivity_withCityCountryAndName_callsMapperWithPartitionAndSortKey() {
        // GIVEN
        String cityCountry = "cityCountry";
        String name = "name";
        when(dynamoDBMapper.load(Activity.class, cityCountry, name)).thenReturn(new Activity());

        // WHEN
        Activity activity = activityDao.getActivity(cityCountry, name);

        // THEN
        assertNotNull(activity);
        verify(dynamoDBMapper).load(Activity.class, cityCountry, name);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETACTIVITY_ACTIVITYNOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getActivity_activityCityCountryAndNameNotFound_throwsActivityNotFoundException() {
        // GIVEN
        String nonexistentCityCountry = "NotReal";
        String nonexistentName = "NotReal";
        when(dynamoDBMapper.load(Activity.class, nonexistentCityCountry)).thenReturn(null);

        // WHEN + THEN
        assertThrows(ActivityNotFoundException.class, () -> activityDao.getActivity(nonexistentCityCountry, nonexistentName));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETACTIVITY_ACTIVITYNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveActivity_callsMapperWithActivity() {
        // GIVEN
        Activity activity = new Activity();

        // WHEN
        Activity result = activityDao.saveActivity(activity);

        // THEN
        verify(dynamoDBMapper).save(activity);
        assertEquals(activity, result);
    }
}
