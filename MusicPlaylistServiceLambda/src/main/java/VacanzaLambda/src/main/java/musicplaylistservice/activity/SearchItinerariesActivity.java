package VacanzaLambda.src.main.java.musicplaylistservice.activity;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.requests.SearchItinerariesRequest;
import VacanzaLambda.src.main.java.musicplaylistservice.activity.results.SearchItinerariesResult;
import VacanzaLambda.src.main.java.musicplaylistservice.converters.VModelConverter;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static VacanzaLambda.src.main.java.musicplaylistservice.utils.NullUtils.ifNull;

public class SearchItinerariesActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new SearchItinerariesActivity object.
     *
     * @param itineraryDao itineraryDao to access the itinerary table.
     */
    @Inject
    public SearchItinerariesActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }


    /**
     * This method handles the incoming request by searching for  itinerary from the database.
     * <p>
     * It then returns the matching itineraries, or an empty result list if none are found.
     *
     * @param searchItinerariesRequest request object containing the search criteria
     * @return searchItinerariesResult result object containing the itineraries that match the
     * search criteria.
     */
    public SearchItinerariesResult handleRequest(final SearchItinerariesRequest searchItinerariesRequest) {
        log.info("Received SearchItinerariesRequest {}", searchItinerariesRequest);
        String criteria = ifNull(searchItinerariesRequest.getCriteria(), "");
       // String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.split("\\s");

        List<Itinerary> results = itineraryDao.searchItinerariesByEmail(criteria);
        List<ItineraryModel> itineraryModels = new VModelConverter().toItineraryModelList(results);

        return SearchItinerariesResult.builder()
                .withItineraries(itineraryModels)
                .build();
    }
}
