package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.GetItineraryRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.GetItineraryResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


/**
 * Implementation of the GetItineraryActivity for VACANZA's GetItinerary API.
 *
 * This API allows the customer to get one of their saved itineraries.
 */
public class GetItineraryActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new GetItineraryActivity object.
     *
     * @param itineraryDao ItineraryDao to access the itinerary table.
     */
    @Inject
    public GetItineraryActivity (ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }

    /**
     * This method handles the incoming request by retrieving the  itinerary from the database.
     * <p>
     * It then returns the itinerary
     *
     * @param getItineraryRequest request object containing the itinerary email and tripName
     * @return getItineraryResult result object containing the API defined {@link ItineraryModel}
     */
    public GetItineraryResult handleRequest(final GetItineraryRequest getItineraryRequest) {
        log.info("Received GetItineraryRequest {}", getItineraryRequest);
        String email = getItineraryRequest.getEmail();
        String tripName= getItineraryRequest.getTripName();
        Itinerary itinerary = itineraryDao.getItinerary(email, tripName);
        ItineraryModel itineraryModel = new VModelConverter().toItineraryModel(itinerary);

        return GetItineraryResult.builder()
                .withItinerary(itineraryModel)
                .build();
    }
    }

