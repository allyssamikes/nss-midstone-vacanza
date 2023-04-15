package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.CreateItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.CreateItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.exceptions.InvalidAttributeValueException;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import java.util.List;

import javax.inject.Inject;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreateItineraryActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param itineraryDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateItineraryActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
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
     * @param createItineraryRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link ItineraryModel}
     */
    public CreateItineraryResult handleRequest(final CreateItineraryRequest createItineraryRequest) {
        log.info("Received CreateRequest {}", createItineraryRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createItineraryRequest.getTripName())) {
            throw new InvalidAttributeValueException("Itinerary name [" + createItineraryRequest.getTripName() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(createItineraryRequest.getEmail())) {
            throw new InvalidAttributeValueException("Playlist customer ID [" + createItineraryRequest.getEmail() +
                    "] contains illegal characters");
        }

        List<String> itineraryTags = null;
        if (createItineraryRequest.getTags() != null) {
            itineraryTags = new ArrayList<>(createItineraryRequest.getTags());
        }
        List<String> itineraryUsers = null;
        if (createItineraryRequest.getUsers() != null) {
            itineraryUsers = new ArrayList<>(createItineraryRequest.getUsers());
        }

        Itinerary newItinerary = new Itinerary();

        newItinerary.setTripName(createItineraryRequest.getTripName());
        newItinerary.setEmail(createItineraryRequest.getEmail());

        newItinerary.setTags(itineraryTags);
        newItinerary.setUsers(itineraryUsers);
        newItinerary.setActivities(new ArrayList<>());

        itineraryDao.saveItinerary(newItinerary);

        ItineraryModel itineraryModel = new VModelConverter().toItineraryModel(newItinerary);
        return CreateItineraryResult.builder()
                .withItineraryModel(itineraryModel)
                .build();
    }
}