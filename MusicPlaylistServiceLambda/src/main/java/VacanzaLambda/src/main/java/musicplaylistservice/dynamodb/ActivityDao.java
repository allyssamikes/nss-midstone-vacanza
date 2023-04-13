package VacanzaLambda.src.main.java.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for an album using activity to represent the model in DynamoDB.
 */
@Singleton
public class ActivityDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an ActivityDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */
    @Inject
    public ActivityDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }



}
