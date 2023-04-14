package VacanzaLambda.src.main.java.musicplaylistservice.dynamodb;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.ActivityNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsConstants;
import VacanzaLambda.src.main.java.musicplaylistservice.metrics.MetricsPublisher;
import javax.inject.Inject;
import javax.inject.Singleton;
/**

 * Accesses data for an activity using {@link Activity} to represent the model in DynamoDB.

 */
@Singleton
public class ActivityDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    /**
     * Instantiates an ActivityDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */
    @Inject
    public ActivityDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }
    /**
     * Retrieves an AlbumTrack by ASIN and track number.
     *
     * If not found, throws ItineraryNotFoundException.
     *
     * @param cityCountry The cityCountry to look up
     * @param name The activity name to look up
     * @return The corresponding Activity if found
     */
    public Activity getActivity(String cityCountry, String name) {
        Activity activity = dynamoDbMapper.load(Activity.class, cityCountry, name);
        if (null == activity) {
            metricsPublisher.addCount(MetricsConstants.GETACTIVITY_ACTIVITYNOTFOUND_COUNT, 1);
            throw new ActivityNotFoundException(
                    String.format("Could not find Activity with cityCountry '%s' and name %s", cityCountry, name));
        }
        metricsPublisher.addCount(MetricsConstants.GETACTIVITY_ACTIVITYNOTFOUND_COUNT, 0);
        return activity;

    }
    public Activity saveActivity(Activity activity) {
        this.dynamoDbMapper.save(activity);
        return activity;
    }
}


