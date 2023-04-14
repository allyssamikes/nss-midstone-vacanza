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
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreateActivityActivity {
    private final Logger log = LogManager.getLogger();
    private final ActivityDao activityDao;

    /**
     * Instantiates a new CreateActivityActivity object.
     *
     * @param activityDao ActivityDao to access the playlists table.
     */
    @Inject
    public CreateActivityActivity(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createActivityRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link ActivityModel}
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

//        Do we need to check for illegal characters for the other none-searching entries?

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
