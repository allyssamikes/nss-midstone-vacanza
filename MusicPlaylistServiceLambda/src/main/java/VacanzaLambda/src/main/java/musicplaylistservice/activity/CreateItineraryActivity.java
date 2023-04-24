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
 * Implementation of the CreateItineraryActivity for the MusicPlaylistService's CreateItinerary API.
 * <p>
 * This API allows the customer to create a new itinerary with no activities.
 */
public class CreateItineraryActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new CreateItineraryActivity object.
     *
     * @param itineraryDao ItineraryDao to access the playlists table.
     */
    @Inject
    public CreateItineraryActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }

    /**
     * This method handles the incoming request by persisting a new itinerary
     * with the provided name and customer email from the request.
     * <p>
     * It then returns the newly created itinerary.
     * <p>
     * If the provided name has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createItineraryRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createItineraryResult result object containing the API defined {@link ItineraryModel}
     */
    public CreateItineraryResult handleRequest(final CreateItineraryRequest createItineraryRequest) {
        log.info("Received CreateRequest {}", createItineraryRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createItineraryRequest.getTripName())) {
            throw new InvalidAttributeValueException("Itinerary name [" + createItineraryRequest.getTripName() +
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
        List<String> itineraryCities = null;
        if (createItineraryRequest.getCities() != null) {
            itineraryCities = new ArrayList<>(createItineraryRequest.getCities());
        }

        Itinerary newItinerary = new Itinerary();

        newItinerary.setTripName(createItineraryRequest.getTripName());
        newItinerary.setEmail(createItineraryRequest.getEmail());
        newItinerary.setTags(itineraryTags);
        newItinerary.setUsers(itineraryUsers);
        newItinerary.setCities(itineraryCities);

        itineraryDao.saveItinerary(newItinerary);

        ItineraryModel itineraryModel = new VModelConverter().toItineraryModel(newItinerary);
        return CreateItineraryResult.builder()
                .withItineraryModel(itineraryModel)
                .build();
    }
}