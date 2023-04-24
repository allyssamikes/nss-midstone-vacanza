package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateActivityRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateActivityResult;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ActivityDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.InvalidAttributeValueException;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateActivityActivity for VACANZA's CreateActivityAPI.
 * <p>
 * This API allows the customer to create a new activity.
 */
public class CreateActivityActivity {
    private final Logger log = LogManager.getLogger();
    private final ActivityDao activityDao;

    /**
     * Instantiates a new CreateActivityActivity object.
     *
     * @param activityDao ActivityDao to access the activities table.
     */
    @Inject
    public CreateActivityActivity(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    /**
     * This method handles the incoming request by persisting a new activity
     * with the provided name and  cityCountry from the request.
     * <p>
     * It then returns the newly created activity.
     * <p>
     * If the provided name or cityCountry  has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createActivityRequest request object containing the name and cityCountry
     *                              associated with it
     * @return createActivityResult result object containing the API defined {@link ActivityModel}
     */
    public CreateActivityResult handleRequest(final CreateActivityRequest createActivityRequest) {
        log.info("Received CreateActivityRequest {}", createActivityRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createActivityRequest.getName())) {
            throw new InvalidAttributeValueException("Activity name [" + createActivityRequest.getName() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(createActivityRequest.getCityCountry())) {
            throw new InvalidAttributeValueException("Activity cityCountry [" + createActivityRequest.getCityCountry() +
                    "] contains illegal characters");
        }

        Activity newActivity = new Activity();
        newActivity.setName(createActivityRequest.getName());
        newActivity.setCityCountry(createActivityRequest.getCityCountry());
        newActivity.setAddress(createActivityRequest.getAddress());
        newActivity.setTYPE_OF_ACTIVITY(createActivityRequest.getType());
        newActivity.setKidFriendly(createActivityRequest.getKidFriendly());
        newActivity.setWeatherPermitting(createActivityRequest.getWeatherPermitting());

        activityDao.saveActivity(newActivity);

        ActivityModel activityModel = new VModelConverter().toActivityModel(newActivity);
        return CreateActivityResult.builder()
                .withActivity(activityModel)
                .build();
    }
}
